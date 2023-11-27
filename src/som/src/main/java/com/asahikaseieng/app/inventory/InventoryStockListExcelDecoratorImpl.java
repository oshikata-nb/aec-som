/*
 * Created on 2008/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.inventorystocklistforreport.InventoryStockListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.
 * @author FPC
 */
public class InventoryStockListExcelDecoratorImpl implements
		InventoryStockListExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/**
	 * コンストラクタ
	 */
	public InventoryStockListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final List<InventoryStockListForReport> list,
			final String tantoName, final Timestamp currentDate) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("inventorystocklist");

		/* シートをセット */
		builder.setSheet("inventorystocklist");

		/* 明細をセット */
		setDetail(list);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	private void setDetail(final List<InventoryStockListForReport> locationList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		for (InventoryStockListForReport bean : locationList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataString(sRow, sCol++, bean.getInoutSourceNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInoutDivision());
			builder.setExcelDataString(sRow, sCol++, bean
					.getInoutDivisionName());
			builder.setExcelDataString(sRow, sCol++, bean.getOderNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOderLineNo());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInoutQty());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInoutDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getItemType());
			builder.setExcelDataString(sRow, sCol++, bean.getReferenceNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getReferenceLineNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getAssignFlag());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOverFlg());

			sRow++;
		}
	}
}
