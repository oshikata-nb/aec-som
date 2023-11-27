/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.claimupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.common.batchwait.BatchWaitLogic;
import com.asahikaseieng.app.master.company.CompanyDetailLogic;
import com.asahikaseieng.app.master.vender.VenderListLogic;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.claim.claimupdate.ClaimUpdate;
import com.asahikaseieng.dao.nonentity.common.batchwait.BatchWaitNamesArray;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.ConstantFunction;

/**
 * 請求更新処理 Actionクラス.(カスタマイズ)
 * @author tosco
 */
public class ClaimUpdateCsmAction extends AbstractAction {

	/** 月末 */
	private static final BigDecimal MONTH_END = new BigDecimal(99);

	/** 未来日チェックフラグ 未来日でない */
	private static final String FLG_NO_FUTURE = "0";

	/** 未来日チェックフラグ 未来日 */
	private static final String FLG_FUTURE = "1";

	/** PL/SQL戻り値 予期せぬエラー */
	// private static final BigDecimal ERROR = new BigDecimal(99);
	/** PL/SQL戻り値 対象データ無しエラー */
	// private static final BigDecimal ERROR_NODATA = new BigDecimal(9);
	/** 取引先区分=TS(得意先) */
	private static final String VENDER_DIVISION_TS = "TS";

	private static final String CLAIM_UPDATE_NUM = "00";

	private ClaimUpdateCsmLogic claimUpdateLogic;

	private VenderListLogic venderListLogic;

	private CompanyDetailLogic companyDetailLogic;

	/** バッチ待ちロジック */
	private BatchWaitLogic batchWaitLogic;

	/**
	 * コンストラクタ.(カスタマイズ)
	 */
	public ClaimUpdateCsmAction() {
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
		ClaimUpdateCsmForm frm = (ClaimUpdateCsmForm) form;

		// 権限取得
		getControlAuthority(request, frm, Constants.MENU_ID_CLAIM_UPDATE,
			Constants.TAB_ID_CLAIM_UPDATE_DETAIL);

		if (!frm.getViewAuthority()) {
			// エラーメッセージ
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		HttpSession session = request.getSession(false);

		if (session != null) {
			// セッションからログイン情報取得
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));

			frm.setOrganizationCd(loginInfo.getOrganizationCd()); // 部署コード設定
			frm.setOrganizationName(loginInfo.getOrganizationName()); // 部署名称設定
			frm.setTantoCd(loginInfo.getTantoCd()); // 担当者コード設定
		}

		frm.setCheckDateFlg(FLG_NO_FUTURE);

		String menuId = String.valueOf(frm.getScreenId());

		if (menuId == null) {
			menuId = String.valueOf(Constants.MENU_ID_CLAIM_UPDATE);
		}

		// プロシージャ名を取得
		String procCd = null;

		List<BatchWaitNamesArray> namesBean = getProcName(menuId,
			CLAIM_UPDATE_NUM);

		for (BatchWaitNamesArray beanBatch : namesBean) {
			procCd = beanBatch.getName01();
		}

		if (StringUtils.isEmpty(procCd)) {
			saveError(request, "errors.batch.no.proccd");
			return mapping.findForward("success");
		}

		// パラメータ情報取得
		ProcParam prm = claimUpdateLogic.getProcParam(procCd);

		if (prm != null) {
			ActionRedirect redirect = new ActionRedirect(mapping
					.findForward("wait")); // (1)
			redirect.addParameter("screenId", frm.getScreenId()); // (2)
			redirect.addParameter("num", CLAIM_UPDATE_NUM);

			// 処理中画面へ
			return redirect;
		}

		// ログ情報取得
		List<ProcedureLog> logList = claimUpdateLogic.getProcLog(procCd, frm
				.getTantoCd());

		if (0 < logList.size()) {
			// 改行コード
			String crlf = System.getProperty("line.separator");

			for (ProcedureLog beanLog : logList) {
				if (beanLog.getFlg().compareTo(BigDecimal.ONE) == 0
						&& StringUtils.isNotEmpty(beanLog.getMsg())) {
					String message = beanLog.getInputDate() + ' '
							+ beanLog.getMsg();

					if (StringUtils.isEmpty(frm.getLogMsg())) {
						frm.setLogMsg(message);
					} else {
						frm.setLogMsg(frm.getLogMsg() + crlf + message);
					}
				}
			}

			// ログフラグ設定
			frm.setLogFlg("true");
		}

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
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		ClaimUpdateCsmForm frm = (ClaimUpdateCsmForm) form;

		// 部署コード存在チェック
		if (claimUpdateLogic.getOrganization(frm.getOrganizationCd()) == null) {
			saveError(request, "errors.nodata.master", rb
					.getString("item.claimupdate.organization.cd"));
			return mapping.getInputForward();
		}

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
		if (frm.isClosingFlg() && StringUtils.isNotEmpty(frm.getVenderCd())) {
			// 請求ヘッダー存在チェック
			if (checkExist(frm)) {
				// 処理済みエラー
				saveError(request, "errors.update.finished");
				return mapping.getInputForward();
			}
		}
		
		// 仮締め の場合
		if (!frm.isClosingFlg()) {
			// 請求ヘッダー存在チェック
			if (checkExist(frm)) {
				// 本締め処理済みエラー
				saveError(request, "errors.update.already.finished");
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

		// 締め日取得
		BigDecimal closingDay = getClosingDay(request);

		// 売掛締め日を設定
		frm.setClosingDate(getClosingDate(frm.getCreditDate(), closingDay));

		// 請求更新処理(PL/SQL)呼出
		// ProClUpdateCallDto dto = claimUpdateLogic.setProcedureDto(frm);
		// dto = claimUpdateLogic.callProcedure(dto);
		// if (dto.getPNumRet() == null || dto.getPNumRet().compareTo(ERROR) ==
		// 0) {
		// // 更新エラー
		// saveError(request, "errors.update.update");
		// claimUpdateLogic.outPutErrorLog(dto.getPErrCd(), dto.getPErrMsg(),
		// dto.getPStrInputorCd());
		// return mapping.getInputForward();
		// }
		// if (dto.getPNumRet().compareTo(ERROR_NODATA) == 0) {
		// // 対象データ無しエラー
		// saveError(request, "errors.nodata");
		// return mapping.getInputForward();
		// }
		//
		// /* メッセージ */
		// saveMessage(request, "message.complete.update");

		String menuId = String.valueOf(frm.getScreenId());

		if (menuId == null) {
			menuId = String.valueOf(Constants.MENU_ID_CLAIM_UPDATE);
		}

		// ログ情報をクリア
		frm.setLogMsg(null);
		frm.setLogFlg(null);

		try {
			// プロシージャ名を取得
			String procCd = null;
			List<BatchWaitNamesArray> namesBean = getProcName(menuId,
				CLAIM_UPDATE_NUM);

			for (BatchWaitNamesArray bean : namesBean) {
				procCd = bean.getName01();
			}

			if (StringUtils.isEmpty(procCd)) {
				saveError(request, "errors.batch.no.proccd");
				return mapping.findForward("success");
			}

			// パラメータ情報登録
			claimUpdateLogic.insertProcParam(procCd, frm);

			// プロシージャ実行フラグを実行中とする
			frm.setProcFlg("true");

			ActionRedirect redirect = new ActionRedirect(mapping
					.findForward("wait")); // (1)
			redirect.addParameter("screenId", frm.getScreenId()); // (2)
			redirect.addParameter("num", CLAIM_UPDATE_NUM);

			// 処理中画面へ
			return redirect;
		} catch (LogicExceptionEx e) {
			frm.setErrorMsg(e.getMessage());
		}

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
	 * 請求ヘッダー存在チェック 部署コード、請求先、請求締め日のデータの存在チェックを行う。
	 * 
	 * @param frm 請求更新Form
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkExist(final ClaimUpdateCsmForm frm) {
		boolean res = false;

		// 売掛ヘッダー検索処理
		List<ClaimUpdate> list = claimUpdateLogic.getSearchList(frm
				.getOrganizationCd(), frm.getVenderCd(), frm.getCreditDate());
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

	/**
	 * 自社マスタの締め日取得
	 * 
	 * 
	 * @param request HttpServletRequest
	 * @return BigDecimal 締め日
	 * @exception NoDataException
	 */
	private BigDecimal getClosingDay(final HttpServletRequest request)
			throws NoDataException {
		// セッションから自社コード取得

		HttpSession session = request.getSession(false);
		if (session == null) {
			throw new NoDataException();
		}
		PasswordCheck chk = (PasswordCheck) (session
				.getAttribute(Constants.COMPANY_INFO_KEY));

		// 自社マスタから締め日取得
		Company bean = companyDetailLogic.getEntity(chk.getCompanyCd());
		return bean.getClosingDay();
	}

	/**
	 * 売掛締め日(画面締め年月＋自社マスタ.締め日)取得<br>
	 * ※未請求額抽出に使用する
	 * 
	 * @param creditDate 請求締め日
	 * @param closingDay 自社マスタ.締め日
	 * @return Date 売掛締め日
	 */
	private Date getClosingDate(final Date creditDate,
			final BigDecimal closingDay) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(creditDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		cal.clear();

		// 締め日が月末(99)指定の場合
		if (closingDay.compareTo(MONTH_END) == 0) {
			cal.set(year, month, 1);
			// 画面締め年月の月末日取得

			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month, endDay);
		} else {
			// 画面締め年月＋自社マスタ.締め日
			cal.set(year, month, closingDay.intValue());
		}

		// 自社マスタ.締め日を指定すると月が変わってしまう場合
		if (month != cal.get(Calendar.MONTH)) {
			cal.set(year, month, 1);
			// 画面締め年月の月末日取得

			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month, endDay);
		}
		Date closingDate = new Date(cal.getTimeInMillis());

		// 請求締め日の方が大きい場合、売掛締め日を１ヶ月後の締め日に進める
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strCreditDate = sdf.format(creditDate);
		String strClosingDate = sdf.format(closingDate);
		if (strCreditDate.compareTo(strClosingDate) > 0) {
			cal.set(year, month, 1);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);

			// 締め日が月末(99)指定の場合
			if (closingDay.compareTo(MONTH_END) == 0) {
				int endDay = cal.getActualMaximum(Calendar.DATE);
				cal.set(year, month, endDay);
			} else {
				// 画面締め年月＋自社マスタ.締め日
				cal.set(year, month, closingDay.intValue());
			}

			// 自社マスタ.締め日を指定すると月が変わってしまう場合
			if (month != cal.get(Calendar.MONTH)) {
				cal.set(year, month, 1);
				// 画面締め年月の月末日取得

				int endDay = cal.getActualMaximum(Calendar.DATE);
				cal.set(year, month, endDay);
			}
			closingDate = new Date(cal.getTimeInMillis());
		}
		return closingDate;
	}

	/**
	 * プロシージャ名を取得
	 * @param menuId メニューID
	 * @param num 枝番
	 * @return List<BatchWaitNamesArray>
	 */
	public List<BatchWaitNamesArray> getProcName(final String menuId,
			final String num) {
		// プロシージャ名取得
		List<BatchWaitNamesArray> namesBean = batchWaitLogic.getBatchNames(
			menuId, num, "PROC");

		return namesBean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * claimUpdateLogicを設定します。
	 * @param claimUpdateLogic ClaimCsmUpdateLogic
	 */
	public void setClaimUpdateLogic(final ClaimUpdateCsmLogic claimUpdateLogic) {
		this.claimUpdateLogic = claimUpdateLogic;
	}

	/**
	 * venderListLogicを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}

	/**
	 * companyDetailLogicを設定します。
	 * @param companyDetailLogic companyDetailLogic
	 */
	public void setCompanyDetailLogic(
			final CompanyDetailLogic companyDetailLogic) {
		this.companyDetailLogic = companyDetailLogic;
	}

	/**
	 * batchWaitLogicを設定します。
	 * @param batchWaitLogic BatchWaitLogic
	 */
	public void setBatchWaitLogic(final BatchWaitLogic batchWaitLogic) {
		this.batchWaitLogic = batchWaitLogic;
	}
}
