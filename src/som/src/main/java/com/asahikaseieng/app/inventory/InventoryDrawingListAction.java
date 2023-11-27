/*
 * Created on 2008/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingList;
import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport.InventoryDrawingListForReport;
import com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty.InventoryDrawingTotalQty;
import com.asahikaseieng.dao.nonentity.itempreorderqty.ItemPreOrderQtyBase;
import com.asahikaseieng.dao.nonentity.itempreorderqty.ItemPreOrderQtyDao;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 品目別引当明細照会 Actionクラス
 * @author FPC
 */
public class InventoryDrawingListAction extends AbstractSearchAction {

	private InventoryDrawingListLogic inventoryDrawingListLogic;

	private InventoryDrawingListExcelDecorator inventoryDrawingListExcelDecorator;

	private static final String KG_CD = "1";
	
	private ItemPreOrderQtyDao itemPreOrderQtyDao;

	/**
	 * コンストラクタ.
	 */
	public InventoryDrawingListAction() {
		super();
	}

	/**
	 * 初期処理.
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

		return mapping.findForward("success");
	}

	/**
	 * 検索処理.
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

		InventoryDrawingListForm frm = (InventoryDrawingListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<InventoryDrawingList>());

		BigDecimal usepossibleQty = new BigDecimal("0");

		InventoryDrawingTotalQty bean = inventoryDrawingListLogic.getTotalQty(frm.getSrhItemCd(), frm.getSrhOtherCompanyCd1());
		
		ItemPreOrderQtyBase preQty = itemPreOrderQtyDao.getPreOrderQty(frm.getSrhItemCd());
		
		if(preQty != null && preQty.getPreOrderQty() != null){
			frm.setStrPreOrderQty(AecNumberUtils.decimalFormatEx(preQty.getPreOrderQty().multiply(new BigDecimal(-1))));
		}else{
			frm.setStrPreOrderQty("0");
		}

		if (bean != null) {
			frm.setInventoryQty(bean.getInventoryQty());
			frm.setBackorderQty(bean.getBackorderQty());
			frm.setFinishQty(bean.getFinishQty());
			frm.setInspectionQty(bean.getInspectionQty());
			frm.setAssignQty(bean.getAssignQty());
			frm.setSalesAssignQty(bean.getSalesAssignQty());
			frm.setUsepossibleQty(bean.getUsepossibleQty());
			frm.setItemUnit(bean.getName01());

			/* 総量の単位はKgなのでKg桁数設定をする */
			// frm.setStrInventoryQty(checker.format(bean
			// .getUnitOfOperationManagement(), bean.getInventoryQty()));
			// frm.setStrInventoryQty(checker
			// .format(KG_CD, bean.getInventoryQty()));
			frm.setStrInventoryQty(AecNumberUtils.decimalFormatEx(bean
					.getInventoryQty()));

			// frm.setStrBackorderQty(checker.format(bean
			// .getUnitOfOperationManagement(), bean.getBackorderQty()));
			// frm.setStrFinishQty(checker.format(bean
			// .getUnitOfOperationManagement(), bean.getFinishQty()));
			// frm.setStrInspectionQty(checker.format(bean
			// .getUnitOfOperationManagement(), bean.getInspectionQty()));
			// frm.setStrAssignQty(checker.format(bean
			// .getUnitOfOperationManagement(), bean.getAssignQty()));
			// frm.setStrSalesAssignQty(checker.format(bean
			// .getUnitOfOperationManagement(), bean.getSalesAssignQty()));
			// frm.setStrUsepossibleQty(checker.format(bean
			// .getUnitOfOperationManagement(), bean.getUsepossibleQty()));
			frm.setStrBackorderQty(AecNumberUtils.decimalFormatEx(bean
					.getBackorderQty()));
			frm.setStrFinishQty(AecNumberUtils.decimalFormatEx(bean
					.getFinishQty()));
			frm.setStrInspectionQty(AecNumberUtils.decimalFormatEx(bean
					.getInspectionQty()));
			frm.setStrAssignQty(AecNumberUtils.decimalFormatEx(bean
					.getAssignQty()));
			frm.setStrSalesAssignQty(AecNumberUtils.decimalFormatEx(bean
					.getSalesAssignQty()));
			frm.setStrUsepossibleQty(AecNumberUtils.decimalFormatEx(bean
					.getUsepossibleQty()));

			usepossibleQty = AecNumberUtils.convertNullToZero(bean
					.getInventoryQty());
		} else {
			frm.setInventoryQty(null);
			frm.setStrInventoryQty(null);
			frm.setBackorderQty(null);
			frm.setStrBackorderQty(null);
			frm.setFinishQty(null);
			frm.setStrFinishQty(null);
			frm.setInspectionQty(null);
			frm.setStrInspectionQty(null);
			frm.setAssignQty(null);
			frm.setStrAssignQty(null);
			frm.setStrSalesAssignQty(null);
			frm.setUsepossibleQty(null);
			frm.setStrUsepossibleQty(null);
			frm.setItemUnit(null);
		}

		/* 検索条件を取得 */
		InventoryDrawingListPagerCondition condition = (InventoryDrawingListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		/* 帳票Excel用検索条件 */
		frm.setRepItemCd(condition.getSrhItemCd());
		frm.setRepOtherCompanyCd1(condition.getSrhOtherCompanyCd1());

		/* 明細取得 */
		frm.setSearchList(inventoryDrawingListLogic.getList(condition, bean));

		String link = null;
		BigDecimal inoutQty = null;

		/* 在庫推移 */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 在庫推移、引当数をセット */
			inoutQty = AecNumberUtils.convertNullToZero(frm.getSearchList()
					.get(i).getInoutQty());

			usepossibleQty = usepossibleQty.add(inoutQty);
			link = null;

			frm.getSearchList().get(i).setInoutScheduleQty(usepossibleQty);

			/* 数値文字列に変換 */
			// frm.getSearchList().get(i).setStrInoutQty(
			// checker.format(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement(), frm.getSearchList()
			// .get(i).getInoutQty()));
			// frm.getSearchList().get(i).setStrPossibleQty(
			// checker.format(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement(), frm.getSearchList()
			// .get(i).getPossibleQty()));
			frm.getSearchList().get(i).setStrInoutQty(
				AecNumberUtils.decimalFormatEx(frm.getSearchList().get(i)
						.getInoutQty()));
			frm.getSearchList().get(i).setStrPossibleQty(
				AecNumberUtils.decimalFormatEx(frm.getSearchList().get(i)
						.getPossibleQty()));

			/* 総量の単位はKgなのでKg桁数設定をする */
			// frm.getSearchList().get(i).setStrInoutScheduleQty(
			// checker.format(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement(), usepossibleQty));
			// frm.getSearchList().get(i).setStrInoutScheduleQty(
			// checker.format(KG_CD, usepossibleQty));
			frm.getSearchList().get(i).setStrInoutScheduleQty(
				AecNumberUtils.decimalFormatEx(usepossibleQty));

			/* 日付文字列に変換 */
			frm.getSearchList().get(i).setStrInoutDate(
				AecDateUtils.dateFormat(frm.getSearchList().get(i)
						.getInoutDate(), "yyyy/MM/dd"));

			if (frm.getSearchList().get(i).getOderNo() != null) {
				/* 受払区分によって、飛ばす画面を分ける */
				if (frm.getSearchList().get(i).getInoutDivision().equals(
					new BigDecimal("1"))
						|| frm.getSearchList().get(i).getInoutDivision()
								.equals(new BigDecimal("2"))) {
					if (frm.getSearchList().get(i).getOderLineNo() != null) {
						if (frm.getSearchList().get(i).getDirectionDivision() != null) {
							if (frm.getSearchList().get(i).getDirectionStatus()
									.compareTo(new BigDecimal("3")) <= 0) {
								link = "/DirectionHeader.do?op=init&directionDivision="
										+ frm.getSearchList().get(i)
												.getDirectionDivision()
												.toString() + "&directionNo=";
							} else {
								link = "/RdirectionHeader.do?op=init&directionDivision="
										+ frm.getSearchList().get(i)
												.getDirectionDivision()
												.toString() + "&directionNo=";
							}
						}
					}
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("3"))) {
					link = "/PurchaseDetail.do?op=init&buySubcontractOrderNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("4"))) {
					link = "/OrderDetail.do?op=init&orderNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("10"))) {
					link = "/InventoryList.do?op=search&srhLocationCd="
							+ frm.getSearchList().get(i).getLocationCd()
							+ "&srhLink=1" + "&srhOderNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("11"))) {
					link = "/InquiryInputList.do?op=search&strSrhCountDate="
							+ frm.getSearchList().get(i).getStrInoutDate()
							+ "&srhLocationCd="
							+ frm.getSearchList().get(i).getLocationCd()
							+ "&srhLink=1" + "&srhOderNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("12"))) {
					link = "/SalesDetailStandard.do?op=search&salesNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("13"))) {
					link = "/SalesDetailKeep.do?op=search&salesNo=";
				}
			}

			frm.getSearchList().get(i).setLink(link);
		}

		return mapping.findForward("success");

	}

	/**
	 * EXCEL作成処理.
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

		InventoryDrawingListForm frm = (InventoryDrawingListForm) form;

		String itemCd = frm.getRepItemCd();
		String otherCompanyCd1 = frm.getRepOtherCompanyCd1();

		List<InventoryDrawingListForReport> list = inventoryDrawingListLogic
				.getListForReport(itemCd, otherCompanyCd1);

		BigDecimal usepossibleQty = AecNumberUtils.convertNullToZero(frm
				.getInventoryQty());

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		BigDecimal inoutQty = null;

		for (InventoryDrawingListForReport bean : list) {
			/* 在庫推移、引当数をセット */
			inoutQty = AecNumberUtils.convertNullToZero(bean.getInoutQty());

			usepossibleQty = usepossibleQty.add(inoutQty);

			bean.setInoutScheduleQty(usepossibleQty);

			/* 数値文字列に変換 */
			bean.setStrInoutQty(checker.format(bean
					.getUnitOfOperationManagement(), bean.getInoutQty()));
			bean.setStrPossibleQty(checker.format(bean
					.getUnitOfOperationManagement(), bean.getPossibleQty()));

			/* 総量の単位はKgなのでKg桁数設定をする */
			// list.get(i).setStrInoutScheduleQty(
			// checker.format(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement(), usepossibleQty));
			bean.setStrInoutScheduleQty(checker.format(KG_CD, usepossibleQty));

			/* 日付文字列に変換 */
			bean.setStrInoutDate(AecDateUtils.dateFormat(bean.getInoutDate(),
				"yyyy/MM/dd"));
		}

		/* Excel作成 */
		FileDownloadInfo info = inventoryDrawingListExcelDecorator
				.createReport(list, getLoginInfo(request).getTantoNm(),
					AecDateUtils.getCurrentTimestamp());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryDrawingListExcelDecoratorを設定します。
	 * @param inventoryDrawingListExcelDecorator
	 *            inventoryDrawingListExcelDecorator
	 */
	public void setInventoryDrawingListExcelDecorator(
			final InventoryDrawingListExcelDecorator inventoryDrawingListExcelDecorator) {
		this.inventoryDrawingListExcelDecorator = inventoryDrawingListExcelDecorator;
	}

	/**
	 * inventoryDrawingListLogicを設定します。
	 * @param inventoryDrawingListLogic inventoryDrawingListLogic
	 */
	public void setInventoryDrawingListLogic(
			final InventoryDrawingListLogic inventoryDrawingListLogic) {
		this.inventoryDrawingListLogic = inventoryDrawingListLogic;
	}

	/**
	 * itemPreOrderQtyDaoを設定します。
	 * @param itemPreOrderQtyDao itemPreOrderQtyDao
	 */
	public void setItemPreOrderQtyDao(ItemPreOrderQtyDao itemPreOrderQtyDao) {
		this.itemPreOrderQtyDao = itemPreOrderQtyDao;
	}
}
