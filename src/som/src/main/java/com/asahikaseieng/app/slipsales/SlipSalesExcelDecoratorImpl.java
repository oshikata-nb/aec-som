/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipsales;

import java.util.List;

import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesDetailListForReport;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListConditionForReport;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.受払照会一覧
 * @author tosco
 */
public class SlipSalesExcelDecoratorImpl implements SlipSalesExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private SlipSalesListLogic slipSalesListLogic;

	/**
	 * コンストラクタ
	 */
	public SlipSalesExcelDecoratorImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final SlipSalesListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("slipsaleslist");

		/* 明細をセット */
		setReport(condition);

		/* 明細をセット */
		setReportInout(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setReportInout(final SlipSalesListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("saleslistinout");

		List<SlipSalesDetailListForReport> list = slipSalesListLogic
				.getReportDetailList(condition);

		// データが有る場合のみ処理
		if (list != null) {

			for (SlipSalesDetailListForReport bean : list) {
				sCol = TEMP_START_COL;
				builder.setExcelDataString(sRow, sCol++, bean.getSalesNo());
				builder.setExcelDataBigDecimal(sRow, sCol++, bean
						.getSalesRowNo());
				builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
				builder
						.setExcelDataString(sRow, sCol++, bean
								.getLocationName());
				builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
				builder.setExcelDataBigDecimal(sRow, sCol++, bean.getQty());
				builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
				builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
				builder
						.setExcelDataTimestamp(sRow, sCol++, bean
								.getInputDate());
				builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
				builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());
				builder.setExcelDataTimestamp(sRow, sCol++, bean
						.getUpdateDate());
				sRow++;
			}
		}
	}

	/**
	 * 帳票Excelデータをセット
	 * @param list List<InoutRecordListForReport>
	 */
	private void setReport(final SlipSalesListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("saleslist");

		List<SlipSalesListForReport> list = slipSalesListLogic
				.getReportHeaderList(condition);

		for (SlipSalesListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getSalesDate());
			builder.setExcelDataString(sRow, sCol++, bean.getSalesNo());
			builder.setExcelDataString(sRow, sCol++, bean.getCancelSalesNo());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOrganizationName());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderCd());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());
			builder.setExcelDataString(sRow, sCol++, bean.getBalanceCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputTantoName());
			builder.setExcelDataString(sRow, sCol++, bean.getSalesTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getSalesTantoName());
			builder.setExcelDataString(sRow, sCol++, bean.getProductLotno());
			builder.setExcelDataString(sRow, sCol++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOrderRowNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesUnitprice());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStandardUnitprice());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStandardDiscount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSpecialDiscount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTmpUnitpriceFlg());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataString(sRow, sCol++, bean
					.getDisbursementLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getDeliveryComp());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getDeliveryUpdateDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getInvoiceUpdateDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInputDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getDeliveryCd());
			builder.setExcelDataString(sRow, sCol++, bean.getDeliveryName1());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxDivision());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxAmount());
			
			builder.setExcelDataString(sRow, sCol++, bean.getTaxCd());
			
			builder.setExcelDataString(sRow, sCol++, bean.getInvoiceCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInvoiceName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSalesAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSalesBasic());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxRatio());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getUpdateFlg());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getConfigExpRatio());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getRatioNotapplySalesUnitprice());
			builder.setExcelDataString(sRow, sCol++, bean.getShippingNo());
			builder.setExcelDataString(sRow, sCol++, bean.getSalesSlipNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesSlipRowNo());
			//20220510 S.Fujimaki Add
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSalesFaxOutput());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSalesMailOutput());
			//20220510 S.Fujimaki Add end
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSlipPublishComp());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getSlipPublishDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getTransmissionDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getLedgerPostDate());
			builder.setExcelDataString(sRow, sCol++, bean.getLedgerPostNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesConvertQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAcceptConvertQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataType());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDataTotalDivision());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getCategoryDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getAccountYears());
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
					.getDepositTargetDivision());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClaimTargetDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getSummaryCd());
			builder.setExcelDataString(sRow, sCol++, bean.getSummary());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getDepositUpdateDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getDepositNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClaimUpdateDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getClaimNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getEraserCompleteDivision());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getEraserCompleteDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getApprovalStatus());
			builder.setExcelDataString(sRow, sCol++, bean.getApprovedby());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getApprovaldate());
			builder.setExcelDataString(sRow, sCol++, bean
					.getChargeOrganizationCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getChargeOrganizationName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getKeepFlag());
			builder.setExcelDataString(sRow, sCol++, bean.getRyCd());
			builder.setExcelDataString(sRow, sCol++, bean.getRyDescription());
			builder.setExcelDataString(sRow, sCol++, bean
					.getHousingLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getHousingLocationName());
			builder.setExcelDataString(sRow, sCol++, bean
					.getPackageDirectionNo());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
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
	 * slipSalesListLogicを設定します。
	 * @param slipSalesListLogic slipSalesListLogic
	 */
	public void setSlipSalesListLogic(
			final SlipSalesListLogic slipSalesListLogic) {
		this.slipSalesListLogic = slipSalesListLogic;
	}
}
