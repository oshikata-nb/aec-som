/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.purchase;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseList;
import com.asahikaseieng.dao.nonentity.purchase.PurchasePagerCondition;
import com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 発注一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class PurchaseListAction extends AbstractSearchAction {

	/** 発注一覧ロジッククラス */
	private PurchaseListLogic purchaseListLogic;

	/** 帳票Excel用Excelデコレータ */
	private PurchaseListExcelDecorator purchaseListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public PurchaseListAction() {
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
		// ***********************
		// 初期表示
		// ***********************
		/* formをPurchaseListFormにキャスト */
		PurchaseListForm frm = (PurchaseListForm) form;
		// 担当者コード
		frm.setSrhTantoCd(getLoginInfo(request).getTantoCd());
		// 担当者名称
		frm.setSrhTantoName(getLoginInfo(request).getTantoNm());
		// 部署コード
		frm.setSrhOrganizationCd(getLoginInfo(request).getOrganizationCd());
		// 部署名称
		frm.setSrhSectionName(getLoginInfo(request).getOrganizationName());

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

		PurchaseListForm frm = (PurchaseListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = purchaseListExcelDecorator.createReport(frm
				.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);

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
		/* formをPurchaseListFormにキャスト */
		PurchaseListForm frm = (PurchaseListForm) form;

		/* 一覧検索条件をクリア */
		frm.setSearchList(new ArrayList<PurchaseList>());

		/* 検索条件を取得 */
		PurchasePagerCondition condition = (PurchasePagerCondition) frm
				.getPager().getPagerCondition();
		/* 検索条件をセット */
		condition
				.setSrhBuySubcontractOrderNo(frm.getSrhBuySubcontractOrderNo());
		condition.setSrhOrderDateFrom(frm.getSrhOrderDateFrom());
		condition.setSrhOrderDateTo(frm.getSrhOrderDateTo());
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd());
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		condition.setSrhOrderDivision(frm.getSrhOrderDivision());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhSuggestedDeliverlimitDateFrom(frm
				.getSrhSuggestedDeliverlimitDateFrom());
		condition.setSrhSuggestedDeliverlimitDateTo(frm
				.getSrhSuggestedDeliverlimitDateTo());
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhStatus(frm.getSrhStatus());
		condition.setSrhOrderSheetNo(frm.getSrhOrderSheetNo());
		condition.setSrhTashaCd(frm.getSrhTashaCd());

		/* 帳票(Excel)用に検索条件を保持 */
		PurchaseListConditionForReport reportCondition = new PurchaseListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 検索 */
		frm.setSearchList(purchaseListLogic.getSearchList(condition));
		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発注一覧ロジッククラスを設定します。
	 * @param purchaseListLogic 発注一覧ロジッククラス
	 */
	public void setPurchaseListLogic(final PurchaseListLogic purchaseListLogic) {
		this.purchaseListLogic = purchaseListLogic;
	}

	/**
	 * 帳票Excel用Excelデコレータを設定します。
	 * @param purchaseListExcelDecorator 帳票Excel用Excelデコレータ
	 */
	public void setPurchaseListExcelDecorator(
			final PurchaseListExcelDecorator purchaseListExcelDecorator) {
		this.purchaseListExcelDecorator = purchaseListExcelDecorator;
	}

}
