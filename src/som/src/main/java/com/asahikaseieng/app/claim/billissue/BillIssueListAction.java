/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.billissue;

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
import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssueList;
import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssuePagerCondition;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 請求書発行 一覧画面処理 Actionクラス.
 * @author tosco
 */
public final class BillIssueListAction extends AbstractSearchAction {

	private BillIssueListLogic billIssueListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private BillIssueListExcelDecorator billIssueListExcelDecorator;

	/* 対象区分 本締め */
	private static final BigDecimal NORMAL = new BigDecimal("0");

	/* 請求書発行 */
	private static final BigDecimal ISSUE = new BigDecimal("1");

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/**
	 * コンストラクタ.
	 */
	public BillIssueListAction() {
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

		BillIssueListForm frm = (BillIssueListForm) form;

		/* 初期表示：出力区分 */
		frm.setSrhBalanceDivision(false);
		frm.setSrhDealingDivision(true);

		/* 初期表示：部門 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部門コード */
			frm.setSrhSectionCd(loginInfo.getOrganizationCd());

			/* 部門マスタ検索 */
			OrganizationDetail bumonDate = organizationDetailLogic
					.getDetailEntity(frm.getSrhSectionCd());
			if (bumonDate != null) {
				/* 初期表示：部門名称 */
				frm.setSrhSectionName(bumonDate.getOrganizationName());
			} else {
				frm.setSrhSectionName("");
			}
		}
		/* 初期表示：デフォルトチェック（未発行のみ） */
		frm.setSrhBillIssueFlg(true);
		/* 初期表示：デフォルトチェック（通常処理分） */
		frm.setSrhNormalFlg(true);

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

		BillIssueListForm frm = (BillIssueListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<BillIssueList>());

		/* 検索条件を取得 */
		BillIssuePagerCondition condition = (BillIssuePagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhSectionCd(frm.getSrhSectionCd());
		condition.setSrhSectionName(frm.getSrhSectionName());
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		condition.setSrhTantoNm(frm.getSrhTantoNm());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhVenderName(frm.getSrhVenderName());
		condition.setSrhCreditDate(frm.getSrhClosingDate());
		condition.setSrhClaimNoFrom(frm.getSrhClaimNoFrom());
		condition.setSrhClaimNoTo(frm.getSrhClaimNoTo());
		condition.setSrhBillIssueFlg(frm.isSrhBillIssueFlg());
		condition.setSrhBalanceDivision(frm.isSrhBalanceDivision());
		condition.setSrhDealingDivision(frm.isSrhDealingDivision());

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

		/* 帳票(Excel)用に検索条件を保持 */
		BillIssueListConditionForReport reportCondition = new BillIssueListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		List<BillIssueList> list = billIssueListLogic.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			list.get(i).setStrClaimAmountForward(
				checker.format(URKINGAKU, list.get(i).getClaimAmountForward())); /* 前回請求額 */
			// list.get(i).setStrOtherCreditAmount(
			// checker.format(URKINGAKU, list.get(i).getOtherCreditAmount()));
			// /* 入金・その他計 */
			list.get(i).setStrOtherCreditAmount(
				checker.format(KINGAKU, list.get(i).getOtherCreditAmount())); /* 入金・その他計 */
			list.get(i).setStrSalesAmount(
				checker.format(URKINGAKU, list.get(i).getSalesAmount())); /* 今回請求額 */
			list.get(i).setStrOtherSales(
				checker.format(URKINGAKU, list.get(i).getOtherSales())); /* その他計 */
			list.get(i).setStrTaxAmount(
				checker.format(TAX_AMOUNT, list.get(i).getTaxAmount())); /* 消費税 */
			list.get(i).setStrClaimAmount(
				checker.format(URKINGAKU, list.get(i).getTotalClaimAmount())); /* 請求合計 */
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

		BillIssueListForm frm = (BillIssueListForm) form;

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

		BillIssueListForm frm = (BillIssueListForm) form;
		ArrayList<String> claimNoList = new ArrayList<String>();

		/* 請求書発行フラグ更新 */
		billIssueListLogic.updateFlg(frm, ISSUE, getLoginInfo(request)
				.getTantoCd());

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		for (BillIssueList bean : frm.getSearchList()) {
			if (!bean.isBillIssueFlg()) {
				continue;
			} else {
				// 処理を行う請求番号リストを作成
				claimNoList.add(bean.getClaimNo());
			}
		}

		if (!claimNoList.isEmpty()) {

			FileDownloadInfo info = null;

			/* 請求書出力処理 */
			info = billIssueListExcelDecorator.createReport(claimNoList,
				loginUserId, null, request.getRemoteAddr(), frm.getSrhNormalTemp());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			frm.setExcelDownloadFlg(true);
		}

		return mapping.findForward("success");

	}

	/**
	 * EXCEL帳票出力処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reportList(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("reportList.");
		}

		BillIssueListForm frm = (BillIssueListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = billIssueListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * BillIssueListLogicを設定します。
	 *
	 * @param billIssueListLogic billIssueListLogic
	 */
	public void setBillIssueListLogic(
			final BillIssueListLogic billIssueListLogic) {
		this.billIssueListLogic = billIssueListLogic;
	}

	/**
	 * OrganizationDetailLogicを設定します。
	 * @param organizationDetailLogic organizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * 請求書検索画面ロジッククラスを設定します。
	 * @param billIssueListExcelDecorator 請求書検索画面ロジッククラス
	 */
	public void setBillIssueListExcelDecorator(
			final BillIssueListExcelDecorator billIssueListExcelDecorator) {
		this.billIssueListExcelDecorator = billIssueListExcelDecorator;
	}
}
