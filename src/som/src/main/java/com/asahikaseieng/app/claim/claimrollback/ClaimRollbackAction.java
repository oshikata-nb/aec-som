/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.claimrollback;

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
import com.asahikaseieng.app.master.vender.VenderListLogic;
import com.asahikaseieng.dao.nonentity.claim.claimrollback.ClaimRollback;
import com.asahikaseieng.dao.nonentity.procedurecall.ProClRollbackCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.ConstantFunction;

/**
 * 請求更新ロールバック処理 Actionクラス.
 * @author tosco
 */
public class ClaimRollbackAction extends AbstractAction {

	/** 取引先区分=TS(得意先) */
	private static final String VENDER_DIVISION_TS = "TS";

	private ClaimRollbackLogic claimRollbackLogic;

	private VenderListLogic venderListLogic;

	/**
	 * コンストラクタ.請求更新ロールバック処理
	 */
	public ClaimRollbackAction() {
		super();
	}

	/**
	 * 請求更新ロールバック処理画面表示処理
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

		ClaimRollbackForm frm = (ClaimRollbackForm) form;

		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションからログイン情報取得
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));
			// 部署コード設定
			frm.setOrganizationCd(loginInfo.getOrganizationCd());
			// 部署名称取得
			frm.setOrganizationName(loginInfo.getOrganizationName());
			// 担当者コード設定
			frm.setTantoCd(loginInfo.getTantoCd());
		}

		return mapping.findForward("success");
	}

	/**
	 * 請求更新ロールバック処理
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

		ClaimRollbackForm frm = (ClaimRollbackForm) form;

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

		// 請求ヘッダー(ロールバック対象)存在チェック
		if (!checkRollbackExist(frm)) {
			// 存在しない場合エラー
			saveError(request, "errors.updaterollback.finished");
			return mapping.getInputForward();
		}

		// 請求ヘッダー(未来締めデータ)存在チェック
		if (checkExist(frm)) {
			// 存在する場合、処理済みエラー
			saveError(request, "errors.updaterollback.finished");
			return mapping.getInputForward();
		}

		// 消込データ存在チェック
		if (checkEraserExist(frm)) {
			// 存在する場合、消込済みエラー
			saveError(request, "errors.updaterollback.eraser");
			return mapping.getInputForward();
		}

		// 請求更新ロールバック処理(PL/SQL)呼出
		ProClRollbackCallDto dto = claimRollbackLogic.setProcedureDto(frm);
		BigDecimal rtn = claimRollbackLogic.callProcedure(dto);
		if (rtn == null || rtn.compareTo(new BigDecimal(0)) != 0) {
			// ロールバックエラー
			saveError(request, "errors.updaterollback.update");
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
	 * 請求ヘッダー(ロールバック対象)存在チェック 請求ヘッダー.請求締め日＝画面請求締め日のデータ存在チェックを行う。
	 * 
	 * @param frm 請求更新ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkRollbackExist(final ClaimRollbackForm frm) {
		boolean res = false;

		// 請求ヘッダー検索処理
		List<ClaimRollback> list = claimRollbackLogic.getSearchRbList(frm
				.getOrganizationCd(), frm.getVenderCd(), frm.getCreditDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 請求ヘッダー(未来締めデータ)存在チェック 画面請求締め日より後に請求更新しているデータの存在チェックを行う。
	 * 
	 * @param frm 請求更新ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkExist(final ClaimRollbackForm frm) {
		boolean res = false;

		// 請求ヘッダー検索処理
		List<ClaimRollback> list = claimRollbackLogic.getSearchList(frm
				.getOrganizationCd(), frm.getVenderCd(), frm.getCreditDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 消込データ存在チェック 売上・入金データで消込されているデータの存在チェックを行う。
	 * 
	 * @param frm 請求更新ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkEraserExist(final ClaimRollbackForm frm) {
		boolean res = false;

		// 消込データ検索処理
		List<ClaimRollback> list = claimRollbackLogic.getSearchEraserList(frm
				.getOrganizationCd(), frm.getVenderCd(), frm.getCreditDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * updateRollbackLogicを設定します。
	 * @param claimRollbackLogic ClaimRollbackLogic
	 */
	public void setClaimRollbackLogic(
			final ClaimRollbackLogic claimRollbackLogic) {
		this.claimRollbackLogic = claimRollbackLogic;
	}

	/**
	 * venderListLogicを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}

}
