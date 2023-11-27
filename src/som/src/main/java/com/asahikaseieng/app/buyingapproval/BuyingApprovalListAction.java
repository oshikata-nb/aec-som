/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.buyingapproval;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalList;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalPagerCondition;
import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 仕入承認画面 Actionクラス.
 * @author tosco
 * 
 */
public final class BuyingApprovalListAction extends AbstractSearchAction {

	/** 仕入承認 ロジッククラス interface */
	private BuyingApprovalListLogic buyingApprovalListLogic;

	/** 仕入承認 帳票Excelクラス interface */
	private BuyingApprovalListExcelDecorator buyingApprovalListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public BuyingApprovalListAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		// formを仕入承認画面 Formクラスにキャスト
		BuyingApprovalListForm frm = (BuyingApprovalListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BUYING_APPROVAL,
			Constants.TAB_ID_BUYING_APPROVAL_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// 仕入区分コンボボックスの作成
		frm.setStockinDivisionCombo(buyingApprovalListLogic
				.createBuyingApprovalStockingDivisionCombobox(true));

		// 初期検索無し
		return mapping.findForward("success");
	}

	/**
	 * 検索処理(検索ボタン押下時)
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

		// formを仕入承認画面 Formクラスにキャスト
		BuyingApprovalListForm frm = (BuyingApprovalListForm) form;

		// クリア
		frm.setSearchList(new ArrayList<BuyingApprovalList>());

		// 検索条件を取得
		BuyingApprovalPagerCondition condition = (BuyingApprovalPagerCondition) frm
				.getPager().getPagerCondition();

		// 検索条件をコピー
		condition
				.setSrhBuySubcontractOrderNo(frm.getSrhBuySubcontractOrderNo());
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd());
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOrderDivision(frm.getSrhOrderDivision());
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhOrderSheetNo(frm.getSrhOrderSheetNo());
		condition.setSrhCategoryDivision(frm.getSrhCategoryDivision());
		condition.setSrhStockingDateFrom(frm.getSrhStockingDateFrom());
		condition.setSrhStockingDateTo(frm.getSrhStockingDateTo());
		condition.setSrhSlipNo(frm.getSrhSlipNo());
		condition.setSrhAcceptDateFrom(frm.getSrhAcceptDateFrom());
		condition.setSrhAcceptDateTo(frm.getSrhAcceptDateTo());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setSrhCancelCheck(frm.isSrhCancelCheck());

		/* 帳票(Excel)用に検索条件を保持 */
		BuyingApprovalListConditionForReport reportCondition = new BuyingApprovalListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(buyingApprovalListLogic.getSearchList(condition));

		return mapping.findForward("success");
	}

	/**
	 * 帳票処理(検索画面の帳票(Excel)ボタン押下時)
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

		BuyingApprovalListForm frm = (BuyingApprovalListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = buyingApprovalListExcelDecorator
				.createReport(frm.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * 承認処理(承認ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approval(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("approval.");
		}

		// formを仕入承認画面 Formクラス.にキャスト
		BuyingApprovalListForm frm = (BuyingApprovalListForm) form;

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		// 承認処理をおこなう
		buyingApprovalListLogic
				.updateApproval(frm.getSearchList(), loginUserId);

		// 承認処理完了メッセージの登録
		saveMessage(request, "message.buying.approval.complete.approval");

		return mapping.findForward("reSearch");
	}

	/**
	 * 否認処理(否認ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward deny(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("deny.");
		}

		// formを仕入承認画面 Formクラス.にキャスト
		BuyingApprovalListForm frm = (BuyingApprovalListForm) form;

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		// 否認処理をおこなう
		buyingApprovalListLogic.updateDeny(frm.getSearchList(), loginUserId);

		// 否認処理完了メッセージの登録
		saveMessage(request, "message.buying.approval.complete.deny");

		return mapping.findForward("reSearch");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
	 * 
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

		// formを仕入承認画面 Formクラス.にキャスト
		BuyingApprovalListForm frm = (BuyingApprovalListForm) form;

		// クリア
		frm.clear();

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 仕入承認画面 ロジッククラスを設定します。
	 * @param buyingApprovalListLogic 仕入承認画面 ロジッククラス
	 */
	public void setBuyingApprovalListLogic(
			final BuyingApprovalListLogic buyingApprovalListLogic) {
		this.buyingApprovalListLogic = buyingApprovalListLogic;
	}

	/**
	 * 仕入承認画面 帳票Excelクラスを設定します。
	 * @param buyingApprovalListExcelDecorator 仕入承認画面 帳票Excelクラス
	 */
	public void setBuyingApprovalListExcelDecorator(
			final BuyingApprovalListExcelDecorator buyingApprovalListExcelDecorator) {
		this.buyingApprovalListExcelDecorator = buyingApprovalListExcelDecorator;
	}
}
