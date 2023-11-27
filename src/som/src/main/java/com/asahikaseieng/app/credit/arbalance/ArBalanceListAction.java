/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.credit.arbalance;

import java.math.BigDecimal;
import java.util.ArrayList;

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
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceList;
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 売掛残高一覧表作成処理 Actionクラス.
 * @author tosco
 */
public final class ArBalanceListAction extends AbstractSearchAction {

	private ArBalanceListLogic arBalanceListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private ArBalanceListExcelDecorator arBalanceListExcelDecorator;

	/* 対象区分 本締め */
	private static final BigDecimal NORMAL = new BigDecimal("0");

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/**
	 * コンストラクタ.
	 */
	public ArBalanceListAction() {
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

		ArBalanceListForm frm = (ArBalanceListForm) form;

		/* 初期表示：出力区分 */
		frm.setSrhCreditAmountDivi("1");

		/* 初期表示：通常処理分 */
		frm.setSrhNormalFlg(true);

		/* 初期表示：部門 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部門コード */
			frm.setSrhSectionCd(loginInfo.getOrganizationCd());

			try {
				/* 部門マスタ検索 */
				OrganizationDetail bumonDate = organizationDetailLogic
						.getDetailEntity(frm.getSrhSectionCd());
				if (bumonDate != null) {
					/* 初期表示：部門名称 */
					frm.setSrhSectionName(bumonDate.getOrganizationName());
				}

				/* 初期表示：対象年月 */
				ArBalanceList bean = arBalanceListLogic.getSearchDate(frm
						.getSrhSectionCd());
				frm.setSrhTargetMonth(bean.getClosingMonth());

			} catch (NoDataException e) {
				// 取得できない場合でもエラーにしない
			}
		}

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

		ArBalanceListForm frm = (ArBalanceListForm) form;

		/* 日付エラー */
		if (!checkYearMonth(frm.getSrhTargetMonth())) {
			saveErrorWithArgs(request, "errors.yyyymm", "item.srhTargetMonth");
			return mapping.findForward("success");
		}

		/* クリア */
		frm.setSearchList(new ArrayList<ArBalanceList>());

		/* 検索条件を取得 */
		ArBalancePagerCondition condition = (ArBalancePagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// 部門コード
		condition.setSrhSectionCd(frm.getSrhSectionCd());
		// 部門名称
		condition.setSrhSectionName(frm.getSrhSectionName());
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
		// 出力区分（売掛残）
		condition.setSrhCreditAmountDivi(frm.getSrhCreditAmountDivi());
		// 出力区分（未収金残）
		// condition.setSrhAccruedDebitDivi(frm.getSrhAccruedDebitDivi());
		// 通常処理分
		// condition.setSrhNormalFlg(frm.isSrhNormalFlg());
		// 仮締処理分
		// condition.setSrhTempClosingFlg(frm.isSrhTempClosingFlg());
		// 対象区分
		if (frm.getSrhNormalTemp().equals(NORMAL)) {
			condition.setSrhNormalFlg(true);
			condition.setSrhTempClosingFlg(false);
		} else {
			condition.setSrhNormalFlg(false);
			condition.setSrhTempClosingFlg(true);
		}

		/* 帳票(Excel)用に検索条件を保持 */
		ArBalanceListConditionForReport reportCondition = new ArBalanceListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 検索 */
		frm.setSearchList(arBalanceListLogic.getSearchList(condition));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 数値文字列に変換 */
			frm.getSearchList().get(i).setStrBalanceForward(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getBalanceForward())); /* 前月残 */
			// frm.getSearchList().get(i).setStrOtherDeposit(
			// checker.format(URKINGAKU, frm.getSearchList().get(i)
			// .getOtherDeposit())); /* 入金・その他計 */
			frm.getSearchList().get(i).setStrOtherDeposit(
				checker.format(KINGAKU, frm.getSearchList().get(i)
						.getOtherDeposit())); /* 入金・その他計 */
			frm.getSearchList().get(i).setStrSalesAmount(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getSalesAmount())); /* 当月売上高 */
			frm.getSearchList().get(i).setStrOtherSales(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getOtherSales())); /* その他計 */
			frm.getSearchList().get(i).setStrTaxAmount(
				checker.format(TAX_AMOUNT, frm.getSearchList().get(i)
						.getTaxAmount())); /* 消費税 */
			frm.getSearchList().get(i).setStrCreditSalesBreakdown(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getCreditSalesBreakdown())); /* 売掛金 */
			frm.getSearchList().get(i).setStrClaimBalance(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getClaimBalance())); /* 請求残高 */
			frm.getSearchList().get(i).setStrClaimForward(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getClaimForward())); /* 未請求残高 */
			frm.getSearchList().get(i).setStrAccruedDebitBreakdown(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getAccruedDebitBreakdown())); /* 未収金 */
			frm.getSearchList().get(i).setStrCreditAmount(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getCreditAmount())); /* 当月残 */
		}

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

		ArBalanceListForm frm = (ArBalanceListForm) form;

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

		ArBalanceListForm frm = (ArBalanceListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = arBalanceListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * 年月チェック
	 * @param frm
	 * @return チェック結果（問題なし：true、問題あり：false）
	 */
	private boolean checkYearMonth(final String targetMonth) {
		String strDate = targetMonth;
		if (strDate.length() == 7) {
			// 日付の追加
			strDate = strDate.concat("/01");
			// 日付フォーマット（不正な日付の場合nullが返る）
			if (AecDateUtils.getTimestampYmdFormat(strDate) == null) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/* -------------------- setter -------------------- */

	/**
	 * ArBalanceListLogicを設定します。
	 * 
	 * @param arBalanceListLogic arBalanceListLogic
	 */
	public void setArBalanceListLogic(
			final ArBalanceListLogic arBalanceListLogic) {
		this.arBalanceListLogic = arBalanceListLogic;
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
	 * arBalanceListExcelDecoratorを設定します。
	 * @param arBalanceListExcelDecorator arBalanceListExcelDecorator
	 */
	public void setArBalanceListExcelDecorator(
			final ArBalanceListExcelDecorator arBalanceListExcelDecorator) {
		this.arBalanceListExcelDecorator = arBalanceListExcelDecorator;
	}
}
