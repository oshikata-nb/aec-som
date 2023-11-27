/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport.InventoryShippingoutListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.在庫出庫入力一覧
 * @author t0011036
 */
public class InventoryShippingoutListExcelDecoratorImpl implements
		InventoryShippingoutListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/**
	 * コンストラクタ
	 */
	public InventoryShippingoutListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final List<InventoryShippingoutListForReport> list,
			final String tantoNm, final Timestamp currentDate) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("inventoryshippingoutlist");

		/* 明細をセット */
		setDetail(list);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 明細をセット
	 * @param list List<InventoryShippingoutListForReport>
	 */
	private void setDetail(final List<InventoryShippingoutListForReport> list) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("inventoryshippingoutlist");

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (InventoryShippingoutListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());

			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemName());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getInventoryQty());

			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getBackorderQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getAssignQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesAssignQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFinishQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInspectionQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInvalidQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFaultQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInventoryCost());

			builder.setExcelDataTimestamp(sRow, sCol++, bean.getIssueDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getStartDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getEndDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLastInDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLastOutDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getLastInventoryDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLastInventoryQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLastInventoryCost());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getNewInventoryCount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLmInventoryQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLmInventoryCost());

			builder.setExcelDataString(sRow, sCol++, bean.getAliasLotNo());

			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInoutQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFraction());

			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPackQty());
			builder.setExcelDataString(sRow, sCol++, bean.getPackUnit());

			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFractionQty());
			builder.setExcelDataString(sRow, sCol++, bean.getFractionUnit());

			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInventoryQtyKg());

			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}
}
