/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.buyingapproval;

import java.util.List;

import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListConditionForReport;
import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.入荷一覧
 * @author tosco
 */
public class BuyingApprovalListExcelDecoratorImpl implements
		BuyingApprovalListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private BuyingApprovalListLogic buyingApprovalListLogic;

	/**
	 * コンストラクタ
	 */
	public BuyingApprovalListExcelDecoratorImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final BuyingApprovalListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("buyingapprovallist");

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
	private void setReport(final BuyingApprovalListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("approvallist");

		List<BuyingApprovalListForReport> list = buyingApprovalListLogic
				.getReportList(condition);

		for (BuyingApprovalListForReport bean : list) {
			sCol = TEMP_START_COL;

			builder.setExcelDataString(sRow, sCol++, bean
					.getBuySubcontractOrderNo());
			builder.setExcelDataString(sRow, sCol++, bean.getOrderSheetNo());
			builder.setExcelDataString(sRow, sCol++, bean.getSlipNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getHousingUnitprice());
			builder.setExcelDataString(sRow, sCol++, bean.getItemQueueName());
			builder.setExcelDataString(sRow, sCol++, bean.getCategoryName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getAcceptDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockingQuantity());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderConvertQuantity());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockingAmount());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getStockingDate());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());
			builder.setExcelDataString(sRow, sCol++, bean.getStatus2Name());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getPaymentInvoiceCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getVenderPaymentName());

			sRow++;
		}
	}

	/**
	 * buyingApprovalListLogicを設定します。
	 * @param buyingApprovalListLogic buyingApprovalListLogic
	 */
	public void setBuyingApprovalListLogic(
			final BuyingApprovalListLogic buyingApprovalListLogic) {
		this.buyingApprovalListLogic = buyingApprovalListLogic;
	}
}
