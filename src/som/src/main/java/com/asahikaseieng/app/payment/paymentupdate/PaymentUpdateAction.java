/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentupdate;

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
import org.apache.struts.action.ActionRedirect;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.common.batchwait.BatchWaitLogic;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.app.master.vender.VenderListLogic;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.common.batchwait.BatchWaitNamesArray;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.payment.paymentupdate.PaymentUpdate;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 支払更新処理 Actionクラス.
 * @author tosco
 */
public class PaymentUpdateAction extends AbstractAction {

	/** 月末 */
	private static final int MONTH_END = 99;

	/** 未来日チェックフラグ 未来日でない */
	private static final String FLG_NO_FUTURE = "0";

	/** 未来日チェックフラグ 未来日 */
	private static final String FLG_FUTURE = "1";

	/** PL/SQL戻り値 予期せぬエラー */
	// private static final BigDecimal ERROR = new BigDecimal(99);
	/** PL/SQL戻り値 対象データ無しエラー */
	// private static final BigDecimal ERROR_NODATA = new BigDecimal(9);
	/** 取引先区分=SI(仕入れ先) */
	private static final String VENDER_DIVISION_SI = "SI";

	private static final String PAYMENT_UPDATE_NUM = "00";

	private OrganizationDetailLogic organizationDetailLogic;

	private PaymentUpdateLogic paymentUpdateLogic;

	private VenderListLogic venderListLogic;

	/** バッチ待ちロジック */
	private BatchWaitLogic batchWaitLogic;

	/**
	 * コンストラクタ.
	 */
	public PaymentUpdateAction() {
		super();
	}

	/**
	 * 支払更新処理画面表示処理
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
		PaymentUpdateForm frm = (PaymentUpdateForm) form;

		// 権限取得
		getControlAuthority(request, frm, Constants.MENU_ID_PAYMENT_UPDATE,
			Constants.TAB_ID_PAYMENT_UPDATE_DETAIL);

		if (!frm.getViewAuthority()) {
			// エラーメッセージ
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// 初期検索
		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションからログイン情報取得
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));

			frm.setOrganizationCd(loginInfo.getOrganizationCd()); // 部署コード設定
			frm.setTantoCd(loginInfo.getTantoCd()); // 担当者コード設定

			// 部署名称取得
			OrganizationDetail bumonBean = organizationDetailLogic
					.getDetailEntity(frm.getOrganizationCd());

			if (bumonBean != null) {
				frm.setOrganizationName(bumonBean.getOrganizationName());
			} else {
				// 名称が取得できない場合でもエラーにしない
				frm.setOrganizationName("");
			}
		}

		String menuId = String.valueOf(frm.getScreenId());

		if (menuId == null) {
			menuId = String.valueOf(Constants.MENU_ID_PAYMENT_UPDATE);
		}

		// プロシージャ名を取得
		String procCd = null;

		List<BatchWaitNamesArray> namesBean = getProcName(menuId,
			PAYMENT_UPDATE_NUM);

		for (BatchWaitNamesArray beanBatch : namesBean) {
			procCd = beanBatch.getName01();
		}

		if (StringUtils.isEmpty(procCd)) {
			saveError(request, "errors.batch.no.proccd");
			return mapping.findForward("success");
		}

		// パラメータ情報取得
		ProcParam prm = paymentUpdateLogic.getProcParam(procCd);

		if (prm != null) {
			ActionRedirect redirect = new ActionRedirect(mapping
					.findForward("wait")); // (1)
			redirect.addParameter("screenId", frm.getScreenId()); // (2)
			redirect.addParameter("num", PAYMENT_UPDATE_NUM);

			// 処理中画面へ
			return redirect;
		}

		// ログ情報取得
		List<ProcedureLog> logList = paymentUpdateLogic.getProcLog(procCd, frm
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
	 * 支払更新処理
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
		PaymentUpdateForm frm = (PaymentUpdateForm) form;

		// 支払締め日設定
		frm.setPayableDate(getPaymentDateFormat(frm.getStrPayableDate()));

		// 支払先存在チェック
		if (StringUtils.isNotEmpty(frm.getVenderCd())) {
			try {
				venderListLogic.checkVenderCd(VENDER_DIVISION_SI, frm
						.getVenderCd());
			} catch (NoDataException e) {
				saveError(request, "errors.payment.vender");
				return mapping.getInputForward();
			}
		}

		String organizationCd = frm.getOrganizationCd(); // 部署コード

		// 部署コードが未入力の時はレコードチェックしない
		if (StringUtils.isNotEmpty(organizationCd)) {
			// 請求ヘッダー存在チェック
			if (checkExist(frm)) {
				// 処理済みエラー
				saveError(request, "errors.paymentupdate.finished");
				return mapping.getInputForward();
			}
		}

		// 請求締め日の未来日チェック
		if (!FLG_FUTURE.equals(frm.getCheckDateFlg())
				&& checkPaymentDate(frm.getPayableDate())) {
			// 未来日警告ポップアップ
			frm.setCheckDateFlg(FLG_FUTURE);
			return mapping.getInputForward();
		} else {
			frm.setCheckDateFlg(FLG_NO_FUTURE);
		}

		// セッションから自社コード取得
		HttpSession session = request.getSession(false);
		PasswordCheck chk = (PasswordCheck) (session
				.getAttribute(Constants.COMPANY_INFO_KEY));

		// 支払更新処理(PL/SQL)呼出
		// ProPaymentUpdateCallDto dto = paymentUpdateLogic.setProcedureDto(frm,
		// chk.getCompanyCd());
		// BigDecimal rtn = paymentUpdateLogic.callProcedure(dto);
		// if (rtn == null || rtn.compareTo(ERROR) == 0) {
		// // 更新エラー
		// saveError(request, "errors.paymentupdate.update");
		// return mapping.getInputForward();
		// }
		// if (rtn.compareTo(ERROR_NODATA) == 0) {
		// // 対象データ無しエラー
		// saveError(request, "errors.nodata");
		// return mapping.getInputForward();
		// }
		//
		// /* メッセージ */
		// saveMessage(request, "message.complete.update");

		String menuId = String.valueOf(frm.getScreenId());

		if (menuId == null) {
			menuId = String.valueOf(Constants.MENU_ID_PAYMENT_UPDATE);
		}

		// ログ情報をクリア
		frm.setLogMsg(null);
		frm.setLogFlg(null);

		try {
			// プロシージャ名を取得
			String procCd = null;
			List<BatchWaitNamesArray> namesBean = getProcName(menuId,
				PAYMENT_UPDATE_NUM);

			for (BatchWaitNamesArray bean : namesBean) {
				procCd = bean.getName01();
			}

			if (StringUtils.isEmpty(procCd)) {
				saveError(request, "errors.batch.no.proccd");
				return mapping.findForward("success");
			}

			// パラメータ情報登録
			paymentUpdateLogic.insertProcParam(procCd, frm, chk.getCompanyCd());

			// プロシージャ実行フラグを実行中とする
			frm.setProcFlg("true");

			ActionRedirect redirect = new ActionRedirect(mapping
					.findForward("wait")); // (1)
			redirect.addParameter("screenId", frm.getScreenId()); // (2)
			redirect.addParameter("num", PAYMENT_UPDATE_NUM);

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
	 * 
	 * @param strPaymentDate 画面支払締め日
	 * @return Date 支払締め日
	 */
	private Date getPaymentDateFormat(final String strPaymentDate) {
		// スラッシュ分割
		String[] ymd = strPaymentDate.split("/");
		int year = Integer.parseInt(ymd[0]);
		int month = Integer.parseInt(ymd[1]);
		int day = Integer.parseInt(ymd[2]);

		Calendar cal = Calendar.getInstance();

		// 締め日が月末(99)指定の場合

		if (day == MONTH_END) {
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
	 * 支払ヘッダー存在チェック 部署、支払先、支払締め日のデータの存在チェックを行う。
	 * 
	 * @param frm 支払更新Form
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 * 
	 */
	private boolean checkExist(final PaymentUpdateForm frm) {
		boolean res = false;

		// 支払ヘッダー検索処理
		List<PaymentUpdate> list = paymentUpdateLogic.getSearchList(frm
				.getOrganizationCd(), frm.getVenderCd(), frm.getPayableDate());
		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 支払締め日の未来日チェック 支払締め日(画面締め年月＋自社マスタ.締め日)のデータが システム日付より未来日かどうかのチェックを行う。
	 * 
	 * @param cleditDate 支払締め日
	 * @return boolean チェック結果（true:未来日 false:未来日でない）
	 * 
	 */
	private boolean checkPaymentDate(final Date paymentDate) {
		boolean res = false;

		// システム日付
		Calendar sysCal = Calendar.getInstance();
		Date sysDate = new Date(sysCal.getTimeInMillis());

		// 請求締め日＞システム日付 の場合
		if (paymentDate.compareTo(sysDate) > 0) {
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
	 * organizationDetailLogicを設定します。
	 * 
	 * @param organizationDetailLogic OrganizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * PaymentUpdateLogicを設定します。
	 * 
	 * @param paymentUpdateLogic PaymentUpdateLogic
	 */
	public void setPaymentUpdateLogic(
			final PaymentUpdateLogic paymentUpdateLogic) {
		this.paymentUpdateLogic = paymentUpdateLogic;
	}

	/**
	 * venderListLogicを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}

	/**
	 * batchWaitLogicを設定します。
	 * @param batchWaitLogic BatchWaitLogic
	 */
	public void setBatchWaitLogic(final BatchWaitLogic batchWaitLogic) {
		this.batchWaitLogic = batchWaitLogic;
	}
}
