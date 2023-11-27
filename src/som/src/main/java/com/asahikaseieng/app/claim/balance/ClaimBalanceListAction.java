/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.balance;

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
import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalanceList;
import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 請求残高一覧表作成処理 Actionクラス.
 * @author tosco
 */
public final class ClaimBalanceListAction extends AbstractSearchAction {

	private ClaimBalanceListLogic balanceListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private ClaimBalanceListExcelDecorator claimBalanceListExcelDecorator;

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/**
	 * コンストラクタ.
	 */
	public ClaimBalanceListAction() {
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

		ClaimBalanceListForm frm = (ClaimBalanceListForm) form;

		/* 初期表示：出力区分 */
		frm.setSrhOutputDivision(true);

		/* 初期表示：通常処理文 */
		frm.setSrhNormalFlg(true);

		/* 初期表示：部門 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部門コード */
			frm.setSrhSectionCd(loginInfo.getOrganizationCd());

			/* 部門マスタ検索 */
			OrganizationDetail organizationDetail = organizationDetailLogic
					.getDetailEntity(frm.getSrhSectionCd());
			if (organizationDetail != null) {
				/* 初期表示：部門名称 */
				frm.setSrhSectionName(organizationDetail.getOrganizationName());
			} else {
				frm.setSrhSectionName("");
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

		ClaimBalanceListForm frm = (ClaimBalanceListForm) form;

		/* 日付エラー */
		if (!checkYearMonth(frm)) {
			saveErrorWithArgs(request, "errors.yyyymm", "item.srhTargetMonth");
			return mapping.findForward("success");
		}

		/* クリア */
		frm.setSearchList(null);

		/* 検索条件を取得 */
		ClaimBalancePagerCondition condition = (ClaimBalancePagerCondition) frm
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
		// 出力区分
		condition.setSrhOutputDivision(frm.isSrhOutputDivision());
		// 通常処理分
		condition.setSrhNormalFlg(frm.isSrhNormalFlg());
		// 仮締処理分
		condition.setSrhTempClosingFlg(!frm.isSrhNormalFlg());

		/* 帳票(Excel)用に検索条件を保持 */
		ClaimBalanceListConditionForReport reportCondition = new ClaimBalanceListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		List<ClaimBalanceList> list = balanceListLogic.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			list.get(i).setStrClaimAmountForward(
				checker.format(URKINGAKU, list.get(i).getClaimAmountForward())); /* 前回請求額 */
			// list.get(i).setStrOtherCredit(
			// checker.format(URKINGAKU, list.get(i).getOtherCredit())); /*
			// 入金・その他計 */
			list.get(i).setStrOtherCredit(
				checker.format(KINGAKU, list.get(i).getOtherCredit())); /* 入金・その他計 */
			list.get(i).setStrSalesAmount(
				checker.format(URKINGAKU, list.get(i).getSalesAmount())); /* 今回売上高 */
			list.get(i).setStrOtherSales(
				checker.format(URKINGAKU, list.get(i).getOtherSales())); /* その他計 */
			list.get(i).setStrTaxAmount(
				checker.format(TAX_AMOUNT, list.get(i).getTaxAmount())); /* 消費税 */
			list.get(i).setStrClaimBalanceAmount(
				checker.format(URKINGAKU, list.get(i).getClaimBalanceAmount())); /* 未請求額 */
			list.get(i).setStrClaimAmount(
				checker.format(URKINGAKU, list.get(i).getClaimAmount())); /* 請求残高 */
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

		ClaimBalanceListForm frm = (ClaimBalanceListForm) form;

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

		ClaimBalanceListForm frm = (ClaimBalanceListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = claimBalanceListExcelDecorator.createReport(frm
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
	private boolean checkYearMonth(final ClaimBalanceListForm frm) {
		String strDate = frm.getSrhTargetMonth();
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
	 * belongListLogicを設定します。
	 * @param balanceListLogic balanceListLogic
	 */
	public void setBelongListLogic(final ClaimBalanceListLogic balanceListLogic) {
		this.balanceListLogic = balanceListLogic;
	}

	/**
	 * organizationDetailLogicを設定します。
	 * @param organizationDetail organizationDetail
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetail) {
		this.organizationDetailLogic = organizationDetail;
	}

	/**
	 * claimBalanceListExcelDecoratorを設定します。
	 * @param claimBalanceListExcelDecorator claimBalanceListExcelDecorator
	 */
	public void setClaimBalanceListExcelDecorator(
			final ClaimBalanceListExcelDecorator claimBalanceListExcelDecorator) {
		this.claimBalanceListExcelDecorator = claimBalanceListExcelDecorator;
	}
}
