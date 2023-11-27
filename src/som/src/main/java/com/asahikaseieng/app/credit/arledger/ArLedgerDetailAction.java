/*
 * Created on 2008/07/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arledger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetail;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetailPagerCondition;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 売掛元帳詳細 Actionクラス.
 * @author tosco
 */
public final class ArLedgerDetailAction extends AbstractSearchAction {

	private ArLedgerDetailLogic arLedgerDetailLogic;

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/* その他 */
	private static final String SONOTA = "SONOTA";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/**
	 * コンストラクタ.
	 */
	public ArLedgerDetailAction() {
		super();
	}

	/**
	 * 詳細画面表示用．検索処理
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @param form ActionForm
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		ArLedgerDetailForm frm = (ArLedgerDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_AR_LEDGER,
			Constants.TAB_ID_AR_LEDGER_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		try {
			/* 初期検索 */
			ArLedgerDetail bean = arLedgerDetailLogic.getSearchDetail(frm
					.getDepositNo(), frm.getTargetKbn());

			bean.setStrBalanceForward(checker.format(URKINGAKU, bean
					.getBalanceForward())); /* 前月繰越 */
			// bean.setStrDepositAmount(checker.format(URKINGAKU, bean
			// .getDepositAmount())); /* 入金額 */
			// bean.setStrOtherDepositAmount(checker.format(URKINGAKU, bean
			// .getOtherDepositAmount())); /* その他入金額 */
			bean.setStrDepositAmount(checker.format(KINGAKU, bean
					.getDepositAmount())); /* 入金額 */
			bean.setStrOtherDepositAmount(checker.format(KINGAKU, bean
					.getOtherDepositAmount())); /* その他入金額 */
			bean.setStrSalesAmount(checker.format(URKINGAKU, bean
					.getSalesAmount())); /* 売上高 */
			bean.setStrReturnedAmount(checker.format(URKINGAKU, bean
					.getReturnedAmount())); /* 返品 */
			bean.setStrDiscountAmount(checker.format(URKINGAKU, bean
					.getDiscountAmount())); /* 値引 */
			bean.setStrOtherSalesAmount(checker.format(URKINGAKU, bean
					.getOtherSalesAmount())); /* その他売上額 */
			bean.setStrOffsetAmount(checker.format(URKINGAKU, bean
					.getOffsetAmount())); /* 相殺 */
			bean.setStrTaxAmount(checker
					.format(TAX_AMOUNT, bean.getTaxAmount())); /* 消費税 */
			bean.setStrCreditSalesBreakdown(checker.format(URKINGAKU, bean
					.getCreditSalesBreakdown())); /* 売掛残（内訳） */
			bean.setStrAccruedDebitBreakdown(checker.format(URKINGAKU, bean
					.getAccruedDebitBreakdown())); /* 未収金（内訳） */
			bean.setStrCreditAmount(checker.format(URKINGAKU, bean
					.getCreditAmount())); /* 売掛残高 */

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		/* 下段一覧用の検索条件を取得 */
		ArLedgerDetailPagerCondition condition = (ArLedgerDetailPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// 売掛番号
		condition.setSrhDepositNo(frm.getDepositNo());
		// 請求先コード
		condition.setSrhVenderCd(frm.getVenderCd());

		try {
			/* 下段明細検索 */
			List<ArLedgerDetail> list = arLedgerDetailLogic
					.getSearchDetailTable(condition, frm.getTargetKbn());

			for (int i = 0; i < list.size(); i++) {
				list.get(i).setStrRowNo(
					checker.format(SONOTA, list.get(i).getRowNo())); /* 行 */
				list.get(i).setStrSales(
					checker.format(URKINGAKU, list.get(i).getSales())); /* 借方売上金額 */
				// list.get(i).setStrCredit(
				// checker.format(URKINGAKU, list.get(i).getCredit())); /*
				// 貸方入金金額 */
				list.get(i).setStrCredit(
					checker.format(KINGAKU, list.get(i).getCredit())); /* 貸方入金金額 */
				list.get(i).setStrBalance(
					checker.format(URKINGAKU, list.get(i).getBalance())); /* 残高 */
			}

			frm.setDetailList(list);
		} catch (NoDataException e) {
			// 明細データが無くてもエラーとせず、詳細画面を表示
		}

		return mapping.findForward("success");
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/* -------------------- setter -------------------- */

	/**
	 * ArLedgerDetailLogicを設定します。
	 * 
	 * @param arLedgerDetailLogic ArLedgerDetailLogic
	 */
	public void setArLedgerDetailLogic(
			final ArLedgerDetailLogic arLedgerDetailLogic) {
		this.arLedgerDetailLogic = arLedgerDetailLogic;
	}

}
