/*
 * Created on 2009/03/09
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.purchasedelivery;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryList;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryListPagerCondition;
import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 納期回答検索画面 Actionクラス.
 * @author tosco
 * 
 */
public final class PurchaseDeliveryListAction extends AbstractSearchAction {

	/** 納期回答検索画面ロジッククラス */
	private PurchaseDeliveryListLogic purchaseDeliveryListLogic;

	/** 帳票Excel出力クラス */
	private PurchaseDeliveryListExcelDecorator purchaseDeliveryListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliveryListAction() {
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

		/* 初期検索無し */
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

		PurchaseDeliveryListForm frm = (PurchaseDeliveryListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<PurchaseDeliveryList>());

		/* 検索条件を取得 */
		PurchaseDeliveryListPagerCondition condition = (PurchaseDeliveryListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition
				.setSrhBuySubcontractOrderNo(frm.getSrhBuySubcontractOrderNo()); // 発注番号
		condition.setSrhStatus(frm.getSrhStatus()); // 購買ステータス
		condition.setSrhOrderDateFrom(frm.getSrhOrderDateFrom()); // 発注日FROM
		condition.setSrhOrderDateTo(frm.getSrhOrderDateTo()); // 発注日TO
		condition.setSrhSuggestedDeliverlimitDateFrom(frm
				.getSrhSuggestedDeliverlimitDateFrom()); // 納品希望日FROM
		condition.setSrhSuggestedDeliverlimitDateTo(frm
				.getSrhSuggestedDeliverlimitDateTo()); // 納品希望日TO
		condition.setSrhLocationCd(frm.getSrhLocationCd()); // 納入先コード
		condition.setSrhVenderCd(frm.getSrhVenderCd()); // 仕入先コード
		condition.setSrhItemCd(frm.getSrhItemCd()); // 品目コード
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1()); // 他社コード１
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd()); // 部署コード
		condition.setSrhTantoCd(frm.getSrhTantoCd()); // 発注者コード
		condition.setSrhOrderDivision(frm.getSrhOrderDivision()); // オーダー区分
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd()); // 担当部署コード
		condition.setSrhOrderSheetNo(frm.getSrhOrderSheetNo()); // 発注書No

		/* 帳票(Excel)用に検索条件を保持 */
		PurchaseDeliveryListConditionForReport reportCondition = new PurchaseDeliveryListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 検索 */
		frm.setSearchList(purchaseDeliveryListLogic.getSearchList(condition));

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

		PurchaseDeliveryListForm frm = (PurchaseDeliveryListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = purchaseDeliveryListExcelDecorator
				.createReport(frm.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);

		return mapping.findForward("success");

	}

	/* -------------------- setter -------------------- */

	/**
	 * 納期回答検索画面ロジッククラスを設定します。
	 * @param purchaseDeliveryListLogic 納期回答検索画面ロジッククラス
	 */
	public void setPurchaseDeliveryListLogic(
			final PurchaseDeliveryListLogic purchaseDeliveryListLogic) {
		this.purchaseDeliveryListLogic = purchaseDeliveryListLogic;
	}

	/**
	 * 帳票Excel出力クラスを設定します。
	 * @param purchaseDeliveryListExcelDecorator 帳票Excel出力クラス
	 */
	public void setPurchaseDeliveryListExcelDecorator(
			final PurchaseDeliveryListExcelDecorator purchaseDeliveryListExcelDecorator) {
		this.purchaseDeliveryListExcelDecorator = purchaseDeliveryListExcelDecorator;
	}

}
