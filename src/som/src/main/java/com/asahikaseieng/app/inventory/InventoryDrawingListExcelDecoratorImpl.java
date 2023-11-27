/*
 * Created on 2008/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport.InventoryDrawingListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.
 * @author FPC
 */
public class InventoryDrawingListExcelDecoratorImpl implements
		InventoryDrawingListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/**
	 * コンストラクタ
	 */
	public InventoryDrawingListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final List<InventoryDrawingListForReport> list,
			final String tantoName, final Timestamp currentDate) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("inventorydrawinglist");

		/* シートをセット */
		builder.setSheet("inventorydrawinglist");

		/* 明細をセット */
		setDetail(list);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	private void setDetail(
			final List<InventoryDrawingListForReport> locationList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		for (InventoryDrawingListForReport bean : locationList) {
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
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemName());

			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInoutScheduleQty());
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
