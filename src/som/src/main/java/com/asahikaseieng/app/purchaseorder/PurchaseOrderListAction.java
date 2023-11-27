/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.purchaseorder;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderList;
import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderPagerCondition;
import com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListConditionForRepor;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 発注書一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class PurchaseOrderListAction extends AbstractSearchAction {

	/** 発注書一覧ロジッククラス */
	private PurchaseOrderListLogic purchaseOrderListLogic;

	/** 発注書出力ロジッククラス */
	private PurchaseOrderListExcelDecorator purchaseOrderListExcelDecorator;

	/** 帳票（Excel)出力ロジッククラス */
	private PurchaseOrderReportListExcelDecorator purchaseOrderReportListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public PurchaseOrderListAction() {
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
		/* formをPurchaseOrderListFormにキャスト */
		PurchaseOrderListForm frm = (PurchaseOrderListForm) form;
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
		PurchaseOrderListForm frm = (PurchaseOrderListForm) form;

		/* 一覧検索条件をクリア */
		frm.setSearchList(new ArrayList<PurchaseOrderList>());
		frm.setPrintCheckBox(Boolean.FALSE);
		if (!frm.getOp().equals("reSearch")) {
			frm.setExcelDownloadFlg(Boolean.FALSE);
		}

		/* 検索条件を取得 */
		PurchaseOrderPagerCondition condition = (PurchaseOrderPagerCondition) frm
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

		// 検索区分に通常検索を設定
		condition.setSearchType(PurchaseStatus.SEARCH_TYPE_NOMAL);
		frm.setSearchType(PurchaseStatus.SEARCH_TYPE_NOMAL);

		// 発注所印刷ONチェックボックスをデフォルトチェック
		frm.setPrintCheckBox(true);

		/* 帳票(Excel)用に検索条件を保持 */
		PurchaseOrderListConditionForRepor reportCondition = new PurchaseOrderListConditionForRepor();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 検索 */
		frm.setSearchList(purchaseOrderListLogic.getSearchList(condition));
		return mapping.findForward("success");
	}

	/**
	 * アラーム検索ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchAlarm(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchAlarm.");
		}
		/* formをPurchaseListFormにキャスト */
		PurchaseOrderListForm frm = (PurchaseOrderListForm) form;

		/* 一覧検索条件をクリア */
		frm.setSearchList(new ArrayList<PurchaseOrderList>());
		frm.setPrintCheckBox(Boolean.FALSE);
		if (!frm.getOp().equals("reSearch")) {
			frm.setExcelDownloadFlg(Boolean.FALSE);
		}

		/* 検索条件を取得 */
		PurchaseOrderPagerCondition condition = (PurchaseOrderPagerCondition) frm
				.getPager().getPagerCondition();

		// 検索区分に検索を設定
		condition.setSearchType(PurchaseStatus.SEARCH_TYPE_ALARM);
		frm.setSearchType(PurchaseStatus.SEARCH_TYPE_ALARM);

		// 発注所印刷ONチェックボックスをデフォルトチェック
		frm.setPrintCheckBox(true);

		/* 帳票(Excel)用に検索条件を保持 */
		PurchaseOrderListConditionForRepor reportCondition = new PurchaseOrderListConditionForRepor();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 検索 */
		frm.setSearchList(purchaseOrderListLogic.getSearchList(condition));
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

		PurchaseOrderListForm frm = (PurchaseOrderListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = purchaseOrderReportListExcelDecorator
				.createReport(frm.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * 更新処理(発注書ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward order(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		String[] orderSheetNoArray = null;

		ArrayList<String> buySubcontractOrderNoArray = new ArrayList<String>();

		if (getLog().isDebugEnabled()) {
			getLog().debug("order.");
		}

		// 担当者コードを取得
		String tantoCd = getLoginInfo(request).getTantoCd();
		String tantoNm = getLoginInfo(request).getTantoNm();
		// 部署コードを取得
		String organizationCd = getLoginInfo(request).getOrganizationCd();

		/* formをPurchaseDetailFormにキャスト */
		PurchaseOrderListForm frm = (PurchaseOrderListForm) form;
		ActionMessages errors = new ActionMessages();

		// マスタ存在チェック
		errors = purchaseOrderListLogic.checkForOrder(frm.getSearchList());
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("success");
		}
		boolean isPrint = frm.isPrintCheckBox();
		frm.setPrintCheckBox(Boolean.FALSE);

		try {
			/* 更新処理を実行 */
			orderSheetNoArray = purchaseOrderListLogic.updata(frm
					.getSearchList(), tantoCd, organizationCd);

			for (PurchaseOrderList bean : frm.getSearchList()) {
				if (!bean.isOrderCheckBox()) {
					continue;
				} else {
					// 処理を行う発注番号リストを作成
					buySubcontractOrderNoArray.add(bean
							.getBuySubcontractOrderNo());
				}
			}

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
		} catch (LogicExceptionEx e) {
			if ("errors.purchase.stock.update".equals(e.getMessage())) {
				// 在庫更新に失敗
				saveError(request, "errors.purchase.stock.update");
			} else {
				throw e;
			}
			return mapping.getInputForward();
		}
		if (isPrint) {
			if (orderSheetNoArray != null) {
				FileDownloadInfo info = null;

				// 発注書発行
				info = purchaseOrderListExcelDecorator
						.createReport(orderSheetNoArray,
							buySubcontractOrderNoArray, tantoNm, AecDateUtils
									.getCurrentTimestamp(), request
									.getRemoteAddr());
				request.getSession(false).setAttribute(
					Constants.DOWNLOAD_FILE_KEY, info);
				frm.setExcelDownloadFlg(true);
			}
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("reSearch");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発注書一覧ロジッククラスを設定します。
	 * @param purchaseOrderListLogic 発注書一覧ロジッククラス
	 */
	public void setPurchaseListLogic(
			final PurchaseOrderListLogic purchaseOrderListLogic) {
		this.purchaseOrderListLogic = purchaseOrderListLogic;
	}

	/**
	 * 発注書出力ロジッククラスを設定します。
	 * @param purchaseOrderListExcelDecorator 発注書出力ロジッククラス
	 */
	public void setPurchaseOrderListExcelDecorator(
			final PurchaseOrderListExcelDecorator purchaseOrderListExcelDecorator) {
		this.purchaseOrderListExcelDecorator = purchaseOrderListExcelDecorator;
	}

	/**
	 * 帳票（Excel）出力ロジッククラスを設定します。
	 * @param purchaseOrderReportListExcelDecorator 帳票（Excel）出力ロジッククラス
	 */
	public void setPurchaseOrderReportListExcelDecorator(
			final PurchaseOrderReportListExcelDecorator purchaseOrderReportListExcelDecorator) {
		this.purchaseOrderReportListExcelDecorator = purchaseOrderReportListExcelDecorator;
	}
}
