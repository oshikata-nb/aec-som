/*
 * Created on 2008/01/17
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
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockList;
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorystocklistforreport.InventoryStockListForReport;
import com.asahikaseieng.dao.nonentity.inventorystocktotalqty.InventoryStockTotalQty;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 品目別予定在庫照会 Actionクラス
 * @author FPC
 */
public class InventoryStockListAction extends AbstractSearchAction {

	private InventoryStockListLogic inventoryStockListLogic;

	private InventoryStockListExcelDecorator inventoryStockListExcelDecorator;

	private static final String KG_CD = "1";

	/**
	 * コンストラクタ.
	 */
	public InventoryStockListAction() {
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

		InventoryStockListForm frm = (InventoryStockListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<InventoryStockList>());

		BigDecimal usepossibleQty = new BigDecimal("0");

		InventoryStockTotalQty bean = inventoryStockListLogic.getTotalQty(frm
				.getSrhItemCd(), frm.getSrhOtherCompanyCd1());

		if (bean != null) {
			frm.setInventoryQty(bean.getInventoryQty());
			// frm.setStrInventoryQty(AecNumberUtils.decimalFormat(bean
			// .getInventoryQty(), "#,##0"));

			// InventoryItemQueueDetail beanItemQueue = inventoryStockListLogic
			// .getItemQueueEntity(frm.getSrhItemCd());
			//
			// if (beanItemQueue == null) {
			// frm.setStrInventoryQty(AecNumberUtils.decimalFormat(bean
			// .getInventoryQty(), "#,##0"));
			// } else {
			// frm.setStrInventoryQty(checker
			// .format(beanItemQueue.getUnitOfOperationManagement(),
			// bean.getInventoryQty()));
			// }
			/* 総量の単位はKgなのでKg桁数設定をする */
			// frm.setStrInventoryQty(checker
			// .format(KG_CD, bean.getInventoryQty()));
			frm.setStrInventoryQty(AecNumberUtils.decimalFormatEx(bean
					.getInventoryQty()));

			usepossibleQty = AecNumberUtils.convertNullToZero(bean
					.getInventoryQty());
		} else {
			frm.setInventoryQty(null);
			frm.setStrInventoryQty(null);
		}

		/* 検索条件を取得 */
		InventoryStockListPagerCondition condition = (InventoryStockListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		/* 帳票Excel用検索条件 */
		frm.setRepItemCd(condition.getSrhItemCd());
		frm.setRepOtherCompanyCd1(condition.getSrhOtherCompanyCd1());

		/* 明細取得 */
		frm.setSearchList(inventoryStockListLogic.getList(condition, bean));

		String link = null;
		BigDecimal inoutQty = null;

		/* 利用可能数 */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 利用可能数、受払予定数、受払ソースNoをセット */
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

		InventoryStockListForm frm = (InventoryStockListForm) form;

		String itemCd = frm.getRepItemCd();
		String otherCompanyCd1 = frm.getRepOtherCompanyCd1();

		// if (!StringUtils.isEmpty(frm.getSrhItemCd())) {
		// itemCd = AecTextUtils.likeFilter(frm.getSrhItemCd());
		// }
		//
		// if (!StringUtils.isEmpty(frm.getSrhOtherCompanyCd1())) {
		// otherCompanyCd1 = AecTextUtils.likeFilter(frm
		// .getSrhOtherCompanyCd1());
		// }

		List<InventoryStockListForReport> list = inventoryStockListLogic
				.getListForReport(itemCd, otherCompanyCd1);

		BigDecimal usepossibleQty = AecNumberUtils.convertNullToZero(frm
				.getInventoryQty());

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (InventoryStockListForReport bean : list) {
			usepossibleQty = usepossibleQty.add(bean.getInoutQty());

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
		FileDownloadInfo info = inventoryStockListExcelDecorator.createReport(
			list, getLoginInfo(request).getTantoNm(), AecDateUtils
					.getCurrentTimestamp());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryStockListLogicを設定します。
	 * @param inventoryStockListLogic inventoryStockListLogic
	 */
	public void setInventoryStockListLogic(
			final InventoryStockListLogic inventoryStockListLogic) {
		this.inventoryStockListLogic = inventoryStockListLogic;
	}

	/**
	 * inventoryStockListExcelDecoratorを設定します。
	 * @param inventoryStockListExcelDecorator inventoryStockListExcelDecorator
	 */
	public void setInventoryStockListExcelDecorator(
			final InventoryStockListExcelDecorator inventoryStockListExcelDecorator) {
		this.inventoryStockListExcelDecorator = inventoryStockListExcelDecorator;
	}
}
