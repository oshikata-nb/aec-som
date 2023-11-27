/*
 * Created on 2008/07/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apledger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetail;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetailPagerCondition;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 買掛元帳詳細 Actionクラス.
 * @author tosco
 */
public final class ApLedgerDetailAction extends AbstractSearchAction {

	private ApLedgerDetailLogic apLedgerDetailLogic;

	/* その他 */
	private static final String SONOTA = "SONOTA";

	/* 仕入金額 */
	private static final String SIKINGAKU = "SIKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/**
	 * コンストラクタ.
	 */
	public ApLedgerDetailAction() {
		super();
	}

	/**
	 * 買掛元帳詳細画面表示処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		ApLedgerDetailForm frm = (ApLedgerDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_AP_LEDGER,
			Constants.TAB_ID_AP_LEDGER_DETAIL);

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
			ApLedgerDetail bean = apLedgerDetailLogic.getSearchDetail(frm
					.getPayableNo(), frm.getTargetKbn());

			/* 数値文字列に変換 */
			// bean.setStrBalanceForward(checker.format(SIKINGAKU, bean
			// .getBalanceForward())); /* 前月繰越 */
			// bean.setStrPaymentAmount(checker.format(SIKINGAKU, bean
			// .getPaymentAmount())); /* 支払額 */
			// bean.setStrOtherPaymentAmount(checker.format(SIKINGAKU, bean
			// .getOtherPaymentAmount())); /* その他支払高 */
			bean.setStrBalanceForward(checker.format(KINGAKU, bean
					.getBalanceForward())); /* 前月繰越 */
			bean.setStrPaymentAmount(checker.format(KINGAKU, bean
					.getPaymentAmount())); /* 支払額 */
			bean.setStrOtherPaymentAmount(checker.format(KINGAKU, bean
					.getOtherPaymentAmount())); /* その他支払高 */
			bean.setStrStockingAmount(checker.format(SIKINGAKU, bean
					.getStockingAmount())); /* 仕入高 */
			bean.setStrReturnedAmount(checker.format(SIKINGAKU, bean
					.getReturnedAmount())); /* 返品 */
			bean.setStrDiscountAmount(checker.format(SIKINGAKU, bean
					.getDiscountAmount())); /* 値引 */
			// bean.setStrOtherStockingAmount(checker.format(SIKINGAKU, bean
			// .getOtherStockingAmount())); /* その他売上高 */
			bean.setStrOtherStockingAmount(checker.format(KINGAKU, bean
					.getOtherStockingAmount())); /* その他売上高 */
			bean.setStrOffsetAmount(checker.format(SIKINGAKU, bean
					.getOffsetAmount())); /* 相殺 */
			bean.setStrTaxAmount(checker
					.format(TAX_AMOUNT, bean.getTaxAmount())); /* 消費税 */
			// bean.setStrPayableAmount(checker.format(SIKINGAKU, bean
			// .getPayableAmount())); /* 買掛残高 */
			// bean.setStrAccountPayableBreakdown(checker.format(SIKINGAKU, bean
			// .getAccountPayableBreakdown())); /* 買掛残高 */
			bean.setStrPayableAmount(checker.format(KINGAKU, bean
					.getPayableAmount())); /* 買掛残高 */
			bean.setStrAccountPayableBreakdown(checker.format(KINGAKU, bean
					.getAccountPayableBreakdown())); /* 買掛残高 */
			bean.setStrArrearageBreakdown(checker.format(SIKINGAKU, bean
					.getArrearageBreakdown())); /* 未払金 */
			bean.setStrExceptBreakdown(checker.format(SIKINGAKU, bean
					.getExceptBreakdown())); /* 以外 */

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		/* 検索条件を取得 */
		ApLedgerDetailPagerCondition condition = (ApLedgerDetailPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// 買掛番号
		condition.setSrhPayableNo(frm.getPayableNo());
		// 支払先コード
		condition.setSrhVenderCd(frm.getVenderCd());

		try {
			/* 下段明細検索 */
			List<ApLedgerDetail> list = apLedgerDetailLogic
					.getSearchDetailTable(condition, frm.getTargetKbn());

			for (int i = 0; i < list.size(); i++) {
				/* 数値文字列に変換 */
				list.get(i).setStrRowNo(
					checker.format(SONOTA, list.get(i).getRowNo())); /* 行 */
				list.get(i).setStrStocking(
					checker.format(SIKINGAKU, list.get(i).getStocking())); /* 借方仕入金額 */
				// list.get(i).setStrPayment(
				// checker.format(SIKINGAKU, list.get(i).getPayment())); /*
				// 貸方支払金額 */
				list.get(i).setStrPayment(
					checker.format(KINGAKU, list.get(i).getPayment())); /* 貸方支払金額 */
				list.get(i).setStrBalance(
					checker.format(SIKINGAKU, list.get(i).getBalance())); /* 残高 */
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
	 * ApLedgerDetailLogicを設定します。
	 * @param apLedgerDetailLogic ApLedgerDetailLogic
	 */
	public void setApLedgerDetailLogic(
			final ApLedgerDetailLogic apLedgerDetailLogic) {
		this.apLedgerDetailLogic = apLedgerDetailLogic;
	}

}
