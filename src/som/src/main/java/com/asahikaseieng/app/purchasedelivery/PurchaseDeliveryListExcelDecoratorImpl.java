/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchasedelivery;

import java.util.List;

import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListConditionForReport;
import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.納期回答一覧
 * @author tosco
 */
public class PurchaseDeliveryListExcelDecoratorImpl implements
		PurchaseDeliveryListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private PurchaseDeliveryListLogic purchaseDeliveryListLogic;

	/**
	 * コンストラクタ
	 */
	public PurchaseDeliveryListExcelDecoratorImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final PurchaseDeliveryListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("purchasedeliverylist");

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
	private void setReport(
			final PurchaseDeliveryListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("deliverylist");

		List<PurchaseDeliveryListForReport> list = purchaseDeliveryListLogic
				.getReportList(condition);

		if (list != null) {
			for (PurchaseDeliveryListForReport bean : list) {
				sCol = TEMP_START_COL;
				builder
						.setExcelDataString(sRow, sCol++, bean
								.getOrderSheetNo());
				builder
						.setExcelDataTimestamp(sRow, sCol++, bean
								.getOrderDate());
				builder.setExcelDataString(sRow, sCol++, bean.getVenderName());
				builder.setExcelDataBigDecimal(sRow, sCol++, bean
						.getOrderCount());
				builder.setExcelDataBigDecimal(sRow, sCol++, bean
						.getIssuedCount());
				builder.setExcelDataBigDecimal(sRow, sCol++, bean
						.getAdjustCount());
				builder.setExcelDataBigDecimal(sRow, sCol++, bean
						.getFixedCount());
				builder.setExcelDataBigDecimal(sRow, sCol++, bean
						.getArrivedAcceptedCount());

				sRow++;
			}
		}
	}

	/**
	 * purchaseDeliveryListLogicを設定します。
	 * @param purchaseDeliveryListLogic purchaseDeliveryListLogic
	 */
	public void setPurchaseDeliveryListLogic(
			final PurchaseDeliveryListLogic purchaseDeliveryListLogic) {
		this.purchaseDeliveryListLogic = purchaseDeliveryListLogic;
	}
}
