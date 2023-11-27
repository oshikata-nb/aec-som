/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.credit.arledger;

import java.math.BigDecimal;
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
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceList;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerList;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerPagerCondition;
import com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArLedgerConditionForReport;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 売掛元帳 Actionクラス.
 * @author tosco
 */
public final class ArLedgerListAction extends AbstractSearchAction {

	private ArLedgerListLogic arLedgerListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private ArLedgerListExcelDecorator arLedgerListExcelDecorator;

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
	public ArLedgerListAction() {
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

		ArLedgerListForm frm = (ArLedgerListForm) form;

		/* 初期表示：部門 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部門コード */
			frm.setSrhSectionCd(loginInfo.getOrganizationCd());

			/* 初期表示：通常処理分 */
			frm.setSrhNormalFlg(true);

			try {
				/* 部門マスタ検索 */
				OrganizationDetail bumonDate = organizationDetailLogic
						.getDetailEntity(frm.getSrhSectionCd());
				if (bumonDate != null) {
					/* 初期表示：部門名称 */
					frm.setSrhSectionName(bumonDate.getOrganizationName());
				}

				/* 初期表示：対象年月 */
				ArBalanceList bean = arLedgerListLogic.getSearchDate(frm
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

		ArLedgerListForm frm = (ArLedgerListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<ArLedgerList>());

		/* 日付エラー */
		if (!checkYearMonth(frm.getSrhTargetMonth())) {
			saveErrorWithArgs(request, "errors.yyyymm", "item.srhTargetMonth");
			return mapping.findForward("success");
		}

		/* 検索条件を取得 */
		ArLedgerPagerCondition condition = (ArLedgerPagerCondition) frm
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
		// 対象区分
		if (frm.getSrhNormalTemp().equals(NORMAL)) {
			frm.setSrhNormalFlg(true);
			frm.setSrhTempClosingFlg(false);
			condition.setSrhNormalFlg(true);
			condition.setSrhTempClosingFlg(false);
		} else {
			frm.setSrhNormalFlg(false);
			frm.setSrhTempClosingFlg(true);
			condition.setSrhNormalFlg(false);
			condition.setSrhTempClosingFlg(true);
		}

		List<ArLedgerList> list = arLedgerListLogic.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			list.get(i).setStrBalanceForward(
				checker.format(URKINGAKU, list.get(i).getBalanceForward())); /* 前月残 */
			// list.get(i).setStrOtherDeposit(
			// checker.format(URKINGAKU, list.get(i).getOtherDeposit())); /*
			// 入金・その他計 */
			list.get(i).setStrOtherDeposit(
				checker.format(KINGAKU, list.get(i).getOtherDeposit())); /* 入金・その他計 */
			list.get(i).setStrSalesAmount(
				checker.format(URKINGAKU, list.get(i).getSalesAmount())); /* 当月売上高 */
			list.get(i).setStrOtherSales(
				checker.format(URKINGAKU, list.get(i).getOtherSales())); /* その他計 */
			list.get(i).setStrTaxAmount(
				checker.format(TAX_AMOUNT, list.get(i).getTaxAmount())); /* 消費税 */
			list.get(i).setStrCreditSalesBreakdown(
				checker
						.format(URKINGAKU, list.get(i)
								.getCreditSalesBreakdown())); /* 売掛残高 */
			list.get(i).setStrAccruedDebitBreakdown(
				checker.format(URKINGAKU, list.get(i)
						.getAccruedDebitBreakdown())); /* 未収金残高 */
			list.get(i).setStrCreditAmount(
				checker.format(URKINGAKU, list.get(i).getCreditAmount())); /* 当月残 */
		}

		frm.setSearchList(list);

		/* 帳票用に検索条件を保持 */
		RepArLedgerConditionForReport reportCondition = new RepArLedgerConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

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

		ArLedgerListForm frm = (ArLedgerListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * 印刷処理
	 * 
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

		ArLedgerListForm listForm = (ArLedgerListForm) form;

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		FileDownloadInfo info = null;

		String targetMonth = listForm.getSrhTargetMonth();
		String targetKbn = null;

		if (listForm.isSrhNormalFlg() && !listForm.isSrhTempClosingFlg()) {
			targetKbn = "0"; // 通常
		} else if (!listForm.isSrhNormalFlg() && listForm.isSrhTempClosingFlg()) {
			targetKbn = "1"; // 仮締め
		} else if (listForm.isSrhNormalFlg() && listForm.isSrhTempClosingFlg()) {
			targetKbn = "2"; // 両方
		}

		/* 買掛元帳出力処理 */
		info = arLedgerListExcelDecorator.createReport(listForm
				.getReportCondition(), targetKbn, targetMonth, loginUserId,
			null, request);

		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		// ExcelダウンロードフラグＯＮ
		listForm.setExcelDownloadFlg(true);

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
	 * ArLedgerListLogicを設定します。
	 * @param arLedgerListLogic arLedgerListLogic
	 */
	public void setArLedgerListLogic(final ArLedgerListLogic arLedgerListLogic) {
		this.arLedgerListLogic = arLedgerListLogic;
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
	 * 売掛元帳検索画面ロジッククラスを設定します。
	 * @param arLedgerListExcelDecorator 売掛元帳検索画面ロジッククラス
	 */
	public void setArLedgerListExcelDecorator(
			final ArLedgerListExcelDecorator arLedgerListExcelDecorator) {
		this.arLedgerListExcelDecorator = arLedgerListExcelDecorator;
	}
}
