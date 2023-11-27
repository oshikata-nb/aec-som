/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchase;

import java.util.List;

import com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListConditionForReport;
import com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.発注一覧
 * @author tosco
 */
public class PurchaseListExcelDecoratorImpl implements
		PurchaseListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private PurchaseListLogic purchaseListLogic;

	/**
	 * コンストラクタ
	 */
	public PurchaseListExcelDecoratorImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final PurchaseListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("purchaselist");

		/* 明細をセット */
		setReport(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setReport(final PurchaseListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("purchaselist");

		List<PurchaseListForReport> list = purchaseListLogic
				.getReportList(condition);
		for (PurchaseListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getPurchaseNo());
			builder.setExcelDataString(sRow, sCol++, bean
					.getBuySubcontractOrderNo());
			builder.setExcelDataString(sRow, sCol++, bean.getOrderDivideNo());
			builder.setExcelDataString(sRow, sCol++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOrderRowNo());
			builder.setExcelDataString(sRow, sCol++, bean.getAspOrderNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getMaterialDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getSiOrderNo());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getOrderDate());
			builder.setExcelDataString(sRow, sCol++, bean
					.getChargeOrganizationCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getChargeOrganizationName());
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrganizationName());
			builder.setExcelDataString(sRow, sCol++, bean.getTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getTantoName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderCd());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			builder.setExcelDataString(sRow, sCol++, bean.getName01());

			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderConvertQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderUnitprice());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getUnitpriceDefineunit());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSupplierOrdAmount());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getSuggestedDeliverlimitDate());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrderSheetRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStatus());
			builder.setExcelDataString(sRow, sCol++, bean.getStatusName());
			builder.setExcelDataString(sRow, sCol++, bean.getOrderSheetNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderSheeFlag());
			builder
					.setExcelDataTimestamp(sRow, sCol++, bean
							.getOrderSheeDate());
			builder.setExcelDataString(sRow, sCol++, bean
					.getOrderSheelTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getOrderSheelTantoName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getGoodHousingSum());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getReplyContentsDivision());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getDeliveryComp());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getNextDeliverlimitDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataType());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDataTotalDivision());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCategoryDivision());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getStockingDate());
			builder.setExcelDataString(sRow, sCol++, bean.getAccountYears());
			builder.setExcelDataString(sRow, sCol++, bean.getSlipNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRowNo());
			builder.setExcelDataString(sRow, sCol++, bean.getCancelSlipNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCancelRowNo());
			builder.setExcelDataString(sRow, sCol++, bean.getSupplierLotno());
			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataString(sRow, sCol++, bean.getStockLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getStockLocationName());
			builder.setExcelDataString(sRow, sCol++, bean
					.getHousingLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getHousingLocationName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getArrivalQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAcceptQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAcceptConvertQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getIncreaseQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockingQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getHousingUnitprice());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFareAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockingAmount());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getAcceptDate());
			builder.setExcelDataString(sRow, sCol++, bean
					.getOrderSheetRemark2());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark2());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes2());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStatus2());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxDivision());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxAmount());
			builder.setExcelDataString(sRow, sCol++, bean.getPayeeCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getAccountDebitSectionCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getAccountDebitSectionNam());
			builder.setExcelDataString(sRow, sCol++, bean
					.getAccountCreditSectionCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getAccountCreditSectionName());
			builder.setExcelDataString(sRow, sCol++, bean.getDebitTitleCd());
			builder.setExcelDataString(sRow, sCol++, bean.getDebitTitleName());
			builder.setExcelDataString(sRow, sCol++, bean.getDebitSubTitleCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getDebitSubTitleName());
			builder.setExcelDataString(sRow, sCol++, bean.getCreditTitleCd());
			builder.setExcelDataString(sRow, sCol++, bean.getCreditTitleName());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCreditSubTitleCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getCreditSubTitleName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPayableTargetDivision());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPaymentTargetDivision());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPayableUpdateDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getPayableNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPaymentUpdateDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getPaymentNo());
			builder.setExcelDataString(sRow, sCol++, bean.getSummaryCd());
			builder.setExcelDataString(sRow, sCol++, bean.getSummary());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getPaymentUpdateDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getPayableUpdateDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getTransmissionDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLabelFlag());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLabelDate());
			builder.setExcelDataString(sRow, sCol++, bean.getLabelTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLabelTantoName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTmpUnitpriceFlg());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInspectMethod());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getMortgageDetailFlg());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInspreceiptWaitQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getBadQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCostEntry());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAdvPurNoticeDecideDivision());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderMortgagedQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSumHousingConvertQuant());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInspectWaitConvertQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getExtractionsQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDefectiveQuantity());
			builder.setExcelDataString(sRow, sCol++, bean.getCheckTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getCheckTantoName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLaboratoryMethod());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getProvisionDivision());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getCheckDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxRatio());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCheckQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSlipIssueDivision());
			builder
					.setExcelDataTimestamp(sRow, sCol++, bean
							.getSlipIssueDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getApprovalStatus());
			builder.setExcelDataString(sRow, sCol++, bean.getApprovedby());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getApprovaldate());
			builder.setExcelDataString(sRow, sCol++, bean.getDeliveryName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}

	/**
	 * purchaseListLogicを設定します。
	 * @param purchaseListLogic purchaseListLogic
	 */
	public void setPurchaseListLogic(final PurchaseListLogic purchaseListLogic) {
		this.purchaseListLogic = purchaseListLogic;
	}
}
