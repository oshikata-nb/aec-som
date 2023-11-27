/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arupdate;

import java.math.BigDecimal;
import java.sql.Date;
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
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.common.batchwait.BatchWaitNamesArray;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.dao.nonentity.credit.arupdate.ArUpdate;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 売掛更新処理 Actionクラス.
 * @author tosco
 */
public class ArUpdateAction extends AbstractAction {

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
	private static final String AR_UPDATE_NUM = "00";

	private ArUpdateLogic arUpdateLogic;

	private CompanyDetailLogic companyDetailLogic;

	/** バッチ待ちロジック */
	private BatchWaitLogic batchWaitLogic;

	/**
	 * コンストラクタ.
	 */
	public ArUpdateAction() {
		super();
	}

	/**
	 * 売掛更新処理 初期表示
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
		ArUpdateForm frm = (ArUpdateForm) form;

		// 権限取得
		getControlAuthority(request, frm, Constants.MENU_ID_AR_UPDATE,
			Constants.TAB_ID_AR_UPDATE_DETAIL);

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

			frm.setOrganizationCd(loginInfo.getOrganizationCd()); // 部署コード
			frm.setOrganizationName(loginInfo.getOrganizationName()); // 部門名称
			frm.setTantoCd(loginInfo.getTantoCd()); // 担当者コード
		}

		// 締め年月取得
		ArUpdate bean = arUpdateLogic.getSearch(frm.getOrganizationCd());
		frm.setClosingMonth(bean.getClosingMonth());

		frm.setCheckDateFlg(FLG_NO_FUTURE);

		String menuId = String.valueOf(frm.getScreenId());

		if (menuId == null) {
			menuId = String.valueOf(Constants.MENU_ID_AR_UPDATE);
		}

		// プロシージャ名を取得
		String procCd = null;

		List<BatchWaitNamesArray> namesBean = getProcName(menuId, AR_UPDATE_NUM);

		for (BatchWaitNamesArray beanBatch : namesBean) {
			procCd = beanBatch.getName01();
		}

		if (StringUtils.isEmpty(procCd)) {
			saveError(request, "errors.batch.no.proccd");
			return mapping.findForward("success");
		}

		// パラメータ情報取得
		ProcParam prm = arUpdateLogic.getProcParam(procCd);

		if (prm != null) {
			ActionRedirect redirect = new ActionRedirect(mapping
					.findForward("wait")); // (1)
			redirect.addParameter("screenId", frm.getScreenId()); // (2)
			redirect.addParameter("num", AR_UPDATE_NUM);

			// 処理中画面へ
			return redirect;
		}

		// ログ情報取得
		List<ProcedureLog> logList = arUpdateLogic.getProcLog(procCd, frm
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
	 * 売掛更新処理 更新処理
	 * 
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

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		ArUpdateForm frm = (ArUpdateForm) form;

		// 部署コード存在チェック
		if (StringUtils.isNotEmpty(frm.getOrganizationCd())) {
			if (arUpdateLogic.getOrganization(frm.getOrganizationCd()) == null) {
				saveError(request, "errors.nodata.master", rb
						.getString("item.arrollback.organization.cd"));
				return mapping.getInputForward();
			}
		}

		// 締め日取得
		BigDecimal closingDay = getClosingDay(request);

		// 売掛締め日設定
		frm.setCreditDate(getCreditDate(frm, closingDay));

		String organizationCd = frm.getOrganizationCd(); /* 部署コード */

		/* 部署コードが未入力の時はレコードチェックしない */
		if (StringUtils.isNotEmpty(organizationCd)) {
			// 売掛ヘッダー存在チェック
			if (checkExist(frm)) {
				// 処理済みエラー
				saveError(request, "errors.arupdate.finished");
				return mapping.getInputForward();
			}
		}

		// 売掛締め日の未来日チェック
		if (!FLG_FUTURE.equals(frm.getCheckDateFlg()) && checkClosingDate(frm)) {
			// 未来日警告ポップアップ
			frm.setCheckDateFlg(FLG_FUTURE);
			return mapping.getInputForward();
		} else {
			frm.setCheckDateFlg(FLG_NO_FUTURE);
		}

		// 売掛更新処理(PL/SQL)呼出
		// ProArUpdateCallDto dto = arUpdateLogic.setProcedureDto(frm);
		// dto = arUpdateLogic.callProcedure(dto);
		// if (dto.getPNumRet() == null || dto.getPNumRet().compareTo(ERROR) ==
		// 0) {
		// // 更新エラー
		// saveError(request, "errors.arupdate.update");
		// arUpdateLogic.outPutErrorLog(dto.getPErrCd(), dto.getPErrMsg(), dto
		// .getPStrInputorCd());
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
			menuId = String.valueOf(Constants.MENU_ID_AR_UPDATE);
		}

		// ログ情報をクリア
		frm.setLogMsg(null);
		frm.setLogFlg(null);

		try {
			// プロシージャ名を取得
			String procCd = null;
			List<BatchWaitNamesArray> namesBean = getProcName(menuId,
				AR_UPDATE_NUM);

			for (BatchWaitNamesArray bean : namesBean) {
				procCd = bean.getName01();
			}

			if (StringUtils.isEmpty(procCd)) {
				saveError(request, "errors.batch.no.proccd");
				return mapping.findForward("success");
			}

			// パラメータ情報登録
			arUpdateLogic.insertProcParam(procCd, frm);

			// プロシージャ実行フラグを実行中とする
			frm.setProcFlg("true");

			ActionRedirect redirect = new ActionRedirect(mapping
					.findForward("wait")); // (1)
			redirect.addParameter("screenId", frm.getScreenId()); // (2)
			redirect.addParameter("num", AR_UPDATE_NUM);

			// 処理中画面へ
			return redirect;
		} catch (LogicExceptionEx e) {
			frm.setErrorMsg(e.getMessage());
		}

		return mapping.findForward("success");
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
	 * 売掛締め日(画面締め年月＋自社マスタ.締め日)取得
	 * 
	 * 
	 * @param frm 売掛更新Form
	 * @param closingDay 自社マスタ.売掛締め日
	 * @return Date 売掛締め日
	 */
	private Date getCreditDate(final ArUpdateForm frm,
			final BigDecimal closingDay) {
		// スラッシュ分割
		String[] ym = frm.getClosingMonth().split("/");
		int year = Integer.parseInt(ym[0]);
		int month = Integer.parseInt(ym[1]);

		Calendar cal = Calendar.getInstance();

		// 締め日が月末(99)指定の場合

		if (closingDay.compareTo(MONTH_END) == 0) {
			cal.set(year, month - 1, 1);
			// 画面締め年月の月末日取得

			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month - 1, endDay);
		} else {
			// 画面締め年月＋自社マスタ.締め日
			cal.set(year, month - 1, closingDay.intValue());
		}

		// 自社マスタ.締め日を指定すると月が変わってしまう場合

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
	 * 売掛ヘッダー存在チェック 部門、画面締め年月＋自社マスタ.締め日のデータの存在チェックを行う。
	 * 
	 * 
	 * @param frm 売掛更新Form
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 * 
	 */
	private boolean checkExist(final ArUpdateForm frm) {
		boolean res = false;

		// 売掛ヘッダー検索処理

		List<ArUpdate> list = arUpdateLogic.getSearchList(frm
				.getOrganizationCd(), frm.getCreditDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 売掛締め日の未来日チェック 売掛締め日(画面締め年月＋自社マスタ.締め日)のデータが
	 * 
	 * システム日付より未来日かどうかのチェックを行う。
	 * 
	 * 
	 * @param frm 売掛更新Form
	 * @return boolean 存在結果（true:未来日 false:未来日でない）
	 * 
	 */
	private boolean checkClosingDate(final ArUpdateForm frm) {
		boolean res = false;

		// 売掛締め日
		Date cleditDate = frm.getCreditDate();

		// システム日付

		Calendar sysCal = Calendar.getInstance();
		Date sysDate = new Date(sysCal.getTimeInMillis());

		// 売掛締め日(画面締め年月＋自社マスタ.締め日)＞システム日付 の場合

		if (cleditDate.compareTo(sysDate) > 0) {
			res = true;
		}

		return res;
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
	 * arUpdateLogicを設定します。
	 * 
	 * @param arUpdateLogic ArUpdateLogic
	 */
	public void setArUpdateLogic(final ArUpdateLogic arUpdateLogic) {
		this.arUpdateLogic = arUpdateLogic;
	}

	/**
	 * companyDetailLogicを設定します。
	 * 
	 * @param companyDetailLogic CompanyDetailLogic
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
