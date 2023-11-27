/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.claimupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.app.master.vender.VenderListLogic;
import com.asahikaseieng.dao.nonentity.claim.claimupdate.ClaimUpdate;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.ProClUpdateCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.ConstantFunction;

/**
 * 請求更新処理 Actionクラス.
 * @author tosco
 */
public class ClaimUpdateAction extends AbstractAction {

	/** 未来日チェックフラグ 未来日でない */
	private static final String FLG_NO_FUTURE = "0";

	/** 未来日チェックフラグ 未来日 */
	private static final String FLG_FUTURE = "1";

	/** PL/SQL戻り値 予期せぬエラー */
	private static final BigDecimal ERROR = new BigDecimal(99);

	/** PL/SQL戻り値 対象データ無しエラー */
	private static final BigDecimal ERROR_NODATA = new BigDecimal(9);

	/** 取引先区分=TS(得意先) */
	private static final String VENDER_DIVISION_TS = "TS";

	private OrganizationDetailLogic organizationDetailLogic;

	private ClaimUpdateLogic claimUpdateLogic;

	private VenderListLogic venderListLogic;

	/**
	 * コンストラクタ.
	 */
	public ClaimUpdateAction() {
		super();
	}

	/**
	 * 請求更新処理画面 初期表示処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		ClaimUpdateForm frm = (ClaimUpdateForm) form;

		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションからログイン情報取得
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));
			// 部門コード設定
			frm.setSectionCd(loginInfo.getOrganizationCd());
			// 担当者コード設定
			frm.setTantoCd(loginInfo.getTantoCd());

			// 部門名称取得
			OrganizationDetail bumonBean = organizationDetailLogic
					.getDetailEntity(frm.getSectionCd());
			if (bumonBean != null) {
				frm.setSectionName(bumonBean.getOrganizationName());
			} else {
				// 名称が取得できない場合でもエラーにしない
				frm.setSectionName("");
			}
		}

		frm.setCheckDateFlg(FLG_NO_FUTURE);

		return mapping.findForward("success");
	}

	/**
	 * 請求更新処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		ClaimUpdateForm frm = (ClaimUpdateForm) form;

		// 請求締め日設定
		frm.setCreditDate(getCreditDate(frm.getStrCreditDate()));

		// 請求先存在チェック
		if (StringUtils.isNotEmpty(frm.getVenderCd())) {
			try {
				venderListLogic.checkVenderCd(VENDER_DIVISION_TS, frm
						.getVenderCd());
			} catch (NoDataException e) {
				saveError(request, "errors.claim.vender");
				return mapping.getInputForward();
			}
		}

		// 本締め かつ 請求先指定の場合
		if (!frm.isTempClosingFlg()
				&& StringUtils.isNotEmpty(frm.getVenderCd())) {
			// 請求ヘッダー存在チェック
			if (checkExist(frm)) {
				// 処理済みエラー
				saveError(request, "errors.update.finished");
				return mapping.getInputForward();
			}
		}

		// 請求締め日の未来日チェック
		if (!FLG_FUTURE.equals(frm.getCheckDateFlg())
				&& checkCreditDate(frm.getCreditDate())) {
			// 未来日警告ポップアップ
			frm.setCheckDateFlg(FLG_FUTURE);
			return mapping.getInputForward();
		} else {
			frm.setCheckDateFlg(FLG_NO_FUTURE);
		}

		// 請求更新処理(PL/SQL)呼出
		ProClUpdateCallDto dto = claimUpdateLogic.setProcedureDto(frm);
		BigDecimal rtn = claimUpdateLogic.callProcedure(dto);
		if (rtn == null || rtn.compareTo(ERROR) == 0) {
			// 更新エラー
			saveError(request, "errors.update.update");
			return mapping.getInputForward();
		}
		if (rtn.compareTo(ERROR_NODATA) == 0) {
			// 対象データ無しエラー
			saveError(request, "errors.nodata");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("success");

	}

	/**
	 * 日にちを正しい年月日に補正した請求締め日取得
	 * 
	 * @param strCreditDate 画面請求締め日
	 * @return Date 請求締め日
	 */
	private Date getCreditDate(final String strCreditDate) {
		// スラッシュ分割
		String[] ymd = strCreditDate.split("/");
		int year = Integer.parseInt(ymd[0]);
		int month = Integer.parseInt(ymd[1]);
		int day = Integer.parseInt(ymd[2]);

		Calendar cal = Calendar.getInstance();

		// 締め日が月末(99)指定の場合
		if (day == ConstantFunction.LASTDAY_OF_MONTH) {
			cal.set(year, month - 1, 1);
			// 画面請求締め日の月末日取得
			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month - 1, endDay);
		} else {
			// 画面請求締め日
			cal.set(year, month - 1, day);
		}

		// 月が変わってしまう場合
		if (month - 1 != cal.get(Calendar.MONTH)) {
			cal.set(year, month - 1, 1);
			// 画面締め年月の月末日取得
			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month - 1, endDay);
		}

		Date creditDate = new Date(cal.getTimeInMillis());

		return creditDate;
	}

	/**
	 * 請求ヘッダー存在チェック 部門、請求先、請求締め日のデータの存在チェックを行う。
	 * 
	 * @param frm 請求更新Form
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkExist(final ClaimUpdateForm frm) {
		boolean res = false;

		// 売掛ヘッダー検索処理
		List<ClaimUpdate> list = claimUpdateLogic.getSearchList(frm
				.getSectionCd(), frm.getVenderCd(), frm.getCreditDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 請求締め日の未来日チェック 請求締め日(画面締め年月＋自社マスタ.締め日)のデータが システム日付より未来日かどうかのチェックを行う。
	 * 
	 * @param cleditDate 請求締め日
	 * @return boolean チェック結果（true:未来日 false:未来日でない）
	 */
	private boolean checkCreditDate(final Date cleditDate) {
		boolean res = false;

		// システム日付
		Calendar sysCal = Calendar.getInstance();
		Date sysDate = new Date(sysCal.getTimeInMillis());

		// 請求締め日＞システム日付 の場合
		if (cleditDate.compareTo(sysDate) > 0) {
			res = true;
		}

		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * organizationDetailLogicを設定します。
	 * @param organizationDetailLogic OrganizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * claimUpdateLogicを設定します。
	 * @param claimUpdateLogic ClaimUpdateLogic
	 */
	public void setClaimUpdateLogic(final ClaimUpdateLogic claimUpdateLogic) {
		this.claimUpdateLogic = claimUpdateLogic;
	}

	/**
	 * venderListLogicを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}

}
