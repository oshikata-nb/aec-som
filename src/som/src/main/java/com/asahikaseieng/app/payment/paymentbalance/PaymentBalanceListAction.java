/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.payment.paymentbalance;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalanceList;
import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListConditionForReport;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 支払残高一覧作成処理 Actionクラス.
 * @author tosco
 */
public final class PaymentBalanceListAction extends AbstractSearchAction {

	private PaymentBalanceListLogic paymentBalanceListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private PaymentBalanceListExcelDecorator paymentBalanceListExcelDecorator;

	/* 仕入金額 */
	private static final String SIKINGAKU = "SIKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/**
	 * コンストラクタ.支払残高一覧
	 */
	public PaymentBalanceListAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		PaymentBalanceListForm frm = (PaymentBalanceListForm) form;

		/* 初期表示：出力区分 */
		frm.setSrhOutputDivision(true);

		/* 初期表示：部署 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部署コード */
			frm.setSrhOrganizationCd(loginInfo.getOrganizationCd());

			/* 部署マスタ検索 */
			OrganizationDetail bumonDate = organizationDetailLogic
					.getDetailEntity(frm.getSrhOrganizationCd());
			if (bumonDate != null) {
				/* 初期表示：部署名称 */
				frm.setSrhOrganizationName(bumonDate.getOrganizationName());
			} else {
				frm.setSrhOrganizationName("");
			}
		}

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		PaymentBalanceListForm frm = (PaymentBalanceListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<PaymentBalanceList>());

		/* 日付エラー */
		if (!checkYearMonth(frm.getSrhTargetMonth())) {
			saveErrorWithArgs(request, "errors.yyyymm", "item.srhTargetMonth");
			return mapping.findForward("success");
		}

		/* 検索条件を取得 */
		PaymentBalancePagerCondition condition = (PaymentBalancePagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// 部署コード
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		// 担当者コード
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		// 担当者名称
		condition.setSrhTantoNm(frm.getSrhTantoNm());
		// 請求先コード
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		// 請求先名称
		condition.setSrhVenderName(frm.getSrhVenderName());
		// 対象年月
		condition.setSrhTargetMonth(frm.getSrhTargetMonth());
		// 出力区分
		condition.setSrhOutputDivision(frm.isSrhOutputDivision());

		/* 帳票(Excel)用に検索条件を保持 */
		PaymentBalanceListConditionForReport reportCondition = new PaymentBalanceListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		List<PaymentBalanceList> list = paymentBalanceListLogic
				.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			// list.get(i).setStrClaimAmountForward(
			// checker.format(SIKINGAKU, list.get(i).getClaimAmountForward()));
			// /* 前回支払予定額 */
			// list.get(i).setStrCreditAmount(
			// checker.format(SIKINGAKU, list.get(i).getCreditAmount())); /*
			// 支払・その他計 */
			list.get(i).setStrClaimAmountForward(
				checker.format(KINGAKU, list.get(i).getClaimAmountForward())); /* 前回支払予定額 */
			list.get(i).setStrCreditAmount(
				checker.format(KINGAKU, list.get(i).getCreditAmount())); /* 支払・その他計 */
			list.get(i).setStrStockingAmount(
				checker.format(SIKINGAKU, list.get(i).getStockingAmount())); /* 今回仕入額 */
			// list.get(i).setStrOtherTotal(
			// checker.format(SIKINGAKU, list.get(i).getOtherTotal())); /* その他計
			// */
			list.get(i).setStrOtherTotal(
				checker.format(KINGAKU, list.get(i).getOtherTotal())); /* その他計 */
			list.get(i).setStrTaxAmount(
				checker.format(TAX_AMOUNT, list.get(i).getTaxAmount())); /* 消費税 */
			// list.get(i).setStrPayableAmount(
			// checker.format(SIKINGAKU, list.get(i).getPayableAmount())); /*
			// 支払残高 */
			list.get(i).setStrPayableAmount(
				checker.format(KINGAKU, list.get(i).getPayableAmount())); /* 支払残高 */
		}

		frm.setSearchList(list);

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		PaymentBalanceListForm frm = (PaymentBalanceListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * 印刷処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		PaymentBalanceListForm frm = (PaymentBalanceListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = paymentBalanceListExcelDecorator
				.createReport(frm.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 年月チェック
	 * @param strDate 対象年月
	 * @return チェック結果（問題なし：true、問題あり：false）
	 */
	private boolean checkYearMonth(final String strDate) {
		String targetMonth = strDate;
		if (targetMonth.length() == 7) {
			// 日付の追加
			targetMonth = targetMonth.concat("/01");
			// 日付フォーマット（不正な日付の場合nullが返る）
			if (AecDateUtils.getTimestampYmdFormat(targetMonth) == null) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/* -------------------- setter -------------------- */

	/**
	 * PaymentBalanceListLogicを設定します。
	 * @param paymentBalanceListLogic paymentBalanceListLogic
	 */
	public void setPaymentBalanceListLogic(
			final PaymentBalanceListLogic paymentBalanceListLogic) {
		this.paymentBalanceListLogic = paymentBalanceListLogic;
	}

	/**
	 * organizationDetailLogicを設定します。
	 * @param organizationDetailLogic organizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * paymentBalanceListExcelDecoratorを設定します。
	 * @param paymentBalanceListExcelDecorator paymentBalanceListExcelDecorator
	 */
	public void setPaymentBalanceListExcelDecorator(
			final PaymentBalanceListExcelDecorator paymentBalanceListExcelDecorator) {
		this.paymentBalanceListExcelDecorator = paymentBalanceListExcelDecorator;
	}
}
