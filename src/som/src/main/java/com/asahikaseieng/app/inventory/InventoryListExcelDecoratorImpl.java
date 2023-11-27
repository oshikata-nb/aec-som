/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLocationListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLotListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLowerLocationListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.在庫照会一覧
 * @author t0011036
 */
public class InventoryListExcelDecoratorImpl implements
		InventoryListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/**
	 * コンストラクタ
	 */
	public InventoryListExcelDecoratorImpl() {
		super();
	}

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.(全て用）
	 * @param locationList locationList
	 * @param lowerLocationList lowerLocationList
	 * @param lotList lotList
	 * @return FileDownloadInfo
	 */
	public FileDownloadInfo createReportAll(
			final List<InventoryLocationListForReport> locationList,
			final List<InventoryLowerLocationListForReport> lowerLocationList,
			final List<InventoryLotListForReport> lotList) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("inventorylist");

		/* シートをセット */
		builder.setSheet("location_inventory");

		// ロケーションデータセット
		setLocation(locationList);

		/* シートをセット */
		builder.setSheet("lower_location_inventory");

		// 下位ロケーションデータセット
		setLowerLocation(lowerLocationList);

		/* シートをセット */
		builder.setSheet("lot_invenroty");

		// ロット在庫データをセット
		setLot(lotList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.(ロケーション在庫用）
	 * @param locationList locationList
	 * @param lowerLocationList lowerLocationList
	 * @return FileDownloadInfo
	 */
	public FileDownloadInfo createReportLocation(
			final List<InventoryLocationListForReport> locationList,
			final List<InventoryLowerLocationListForReport> lowerLocationList) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("inventorylist");

		/* シートをセット */
		builder.setSheet("location_inventory");

		// ロケーションデータセット
		setLocation(locationList);

		/* シートをセット */
		builder.setSheet("lower_location_inventory");

		// 下位ロケーションデータセット
		setLowerLocation(lowerLocationList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.（ロット在庫用）
	 * @param lotList lotList
	 * @return FileDownloadInfo
	 */
	public FileDownloadInfo createReportLot(
			final List<InventoryLotListForReport> lotList) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("inventorylist");

		/* シートをセット */
		builder.setSheet("lot_invenroty");

		// ロット在庫データをセット
		setLot(lotList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());

	}

	/**
	 * 帳票Excelデータをセット
	 * @param list List<InoutRecordListForReport>
	 */
	private void setLot(final List<InventoryLotListForReport> locationList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		for (InventoryLotListForReport bean : locationList) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPackQty());
			builder.setExcelDataString(sRow, sCol++, bean.getPackUnit());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFractionNum());
			builder.setExcelDataString(sRow, sCol++, bean.getFractionUnit());
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
	 * 帳票Excelデータをセット
	 * @param list List<InoutRecordListForReport>
	 */
	private void setLocation(
			final List<InventoryLocationListForReport> locationList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		for (InventoryLocationListForReport bean : locationList) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPackQty());
			builder.setExcelDataString(sRow, sCol++, bean.getPackUnit());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFractionNum());
			builder.setExcelDataString(sRow, sCol++, bean.getFractionUnit());
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
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLastInDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLastOutDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getLastInventoryDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLastInventoryQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLastInventoryCost());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getNewInventoryQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLmInventoryQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLmInventoryCost());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInoutQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFraction());
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
	 * 帳票Excelデータをセット
	 * @param list List<InoutRecordListForReport>
	 */
	private void setLowerLocation(
			final List<InventoryLowerLocationListForReport> locationList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		for (InventoryLowerLocationListForReport bean : locationList) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPackQty());
			builder.setExcelDataString(sRow, sCol++, bean.getPackUnit());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFractionNum());
			builder.setExcelDataString(sRow, sCol++, bean.getFractionUnit());
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
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLastInDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLastOutDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getLastInventoryDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLastInventoryQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLastInventoryCost());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getNewInventoryQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLmInventoryQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getLmInventoryCost());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInoutQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFraction());
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
