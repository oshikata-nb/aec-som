/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentrollback;

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
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.payment.paymentrollback.PaymentRollback;
import com.asahikaseieng.dao.nonentity.procedurecall.ProPaymentRollbackCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 支払更新ロールバック処理 Actionクラス.
 * @author tosco
 */
public class PaymentRollbackAction extends AbstractAction {

	/** 月末 */
	private static final int MONTH_END = 99;

	/** 取引先区分=SI(仕入れ先) */
	private static final String VENDER_DIVISION_SI = "SI";

	private OrganizationDetailLogic organizationDetailLogic;

	private PaymentRollbackLogic paymentRollbackLogic;

	private VenderListLogic venderListLogic;

	/**
	 * コンストラクタ.支払更新ロールバック処理
	 */
	public PaymentRollbackAction() {
		super();
	}

	/**
	 * 支払更新ロールバック処理画面表示処理
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

		PaymentRollbackForm frm = (PaymentRollbackForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PAYMENT_ROLLBACK,
			Constants.TAB_ID_PAYMENT_ROLLBACK_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		/* 初期検索 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションからログイン情報取得
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));
			// 部署コード設定
			frm.setOrganizationCd(loginInfo.getOrganizationCd());
			// 担当者コード設定
			frm.setTantoCd(loginInfo.getTantoCd());

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

		return mapping.findForward("success");
	}

	/**
	 * 支払更新ロールバック処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward rollback(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("rollback.");
		}

		PaymentRollbackForm frm = (PaymentRollbackForm) form;

		/* 支払締め日設定 */
		frm.setPayableDate(getPayableDateFormat(frm.getStrPayableDate()));

		/* 支払先存在チェック */
		if (StringUtils.isNotEmpty(frm.getVenderCd())) {
			try {
				venderListLogic.checkVenderCd(VENDER_DIVISION_SI, frm
						.getVenderCd());
			} catch (NoDataException e) {
				saveError(request, "errors.payment.vender");
				return mapping.getInputForward();
			}
		}

		String organizationCd = frm.getOrganizationCd(); /* 部署コード */

		/* 部署コードが未入力の時はレコードチェックしない */
		if (StringUtils.isNotEmpty(organizationCd)) {
			/* 支払ヘッダー(ロールバック対象)存在チェック */
			if (!checkRollbackExist(frm)) {
				/* 存在しない場合エラー */
				saveError(request, "errors.paymentrollback.finished");
				return mapping.getInputForward();
			}

			/* 支払ヘッダー(未来締めデータ)存在チェック */
			if (checkExist(frm)) {
				/* 存在する場合、処理済みエラー */
				saveError(request, "errors.paymentrollback.finished");
				return mapping.getInputForward();
			}

			// ※消し込みカスタマイズを攝津標準としたため、このチェックは不要とする
			// // 消込データ存在チェック
			// if (checkEraserExist(frm)) {
			// // 存在する場合、消込済みエラー
			// saveError(request, "errors.paymentrollback.eraser");
			// return mapping.getInputForward();
			// }
		}

		/* 支払更新ロールバック処理(PL/SQL)呼出 */
		ProPaymentRollbackCallDto dto = paymentRollbackLogic
				.setProcedureDto(frm);
		BigDecimal rtn = paymentRollbackLogic.callProcedure(dto);
		if (rtn == null || rtn.compareTo(new BigDecimal(0)) != 0) {
			/* ロールバックエラー */
			saveError(request, "errors.paymentrollback.update");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("success");
	}

	/**
	 * 日にちを正しい年月日に補正した支払締め日取得
	 * 
	 * @param strCreditDate 画面支払締め日
	 * @return Date 支払締め日
	 */
	private Date getPayableDateFormat(final String strCreditDate) {
		// スラッシュ分割
		String[] ymd = strCreditDate.split("/");
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
	 * 支払ヘッダー(ロールバック対象)存在チェック 支払ヘッダー.支払締め日＝画面支払締め日のデータ存在チェックを行う。
	 * 
	 * @param frm 支払更新ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkRollbackExist(final PaymentRollbackForm frm) {
		boolean res = false;

		// 支払ヘッダー検索処理
		List<PaymentRollback> list = paymentRollbackLogic.getSearchRbList(frm
				.getOrganizationCd(), frm.getVenderCd(), frm.getPayableDate());

		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	/**
	 * 支払ヘッダー(未来締めデータ)存在チェック 画面支払締め日より後に支払更新しているデータの存在チェックを行う。
	 * 
	 * @param frm 支払更新ロールバックForm
	 * @return boolean 存在結果（true:存在する false:存在しない）
	 */
	private boolean checkExist(final PaymentRollbackForm frm) {
		boolean res = false;

		// 支払ヘッダー検索処理
		List<PaymentRollback> list = paymentRollbackLogic.getSearchList(frm
				.getOrganizationCd(), frm.getVenderCd(), frm.getPayableDate());

		if (list.size() > 0) {
			res = true;
		}

		return res;
	}

	// ※消し込みカスタマイズを攝津標準としたため、このチェックは不要とする
	// /**
	// * 消込データ存在チェック 入金・支払・グループ間相殺データで消込されているデータの存在チェックを行う。
	// *
	// * @param frm 支払更新ロールバックForm
	// * @return boolean 存在結果（true:存在する false:存在しない）
	// */
	// private boolean checkEraserExist(final PaymentRollbackForm frm) {
	// boolean res = false;
	//
	// // 消込データ検索処理
	// List<PaymentRollback> list = paymentRollbackLogic.getSearchEraserList(
	// frm.getOrganizationCd(), frm.getVenderCd(), frm.getPayableDate());
	// if (list.size() > 0) {
	// res = true;
	// }
	//
	// return res;
	// }

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
	 * PaymentRollbackLogicを設定します。
	 * @param paymentRollbackLogic PaymentRollbackLogic
	 */
	public void setPaymentRollbackLogic(
			final PaymentRollbackLogic paymentRollbackLogic) {
		this.paymentRollbackLogic = paymentRollbackLogic;
	}

	/**
	 * venderListLogicを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}

}
