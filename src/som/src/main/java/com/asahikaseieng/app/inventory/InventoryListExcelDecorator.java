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
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.在庫照会一覧
 * @author t0011036
 */
public interface InventoryListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.(ロケーション在庫用）
	 * @param locationList locationList
	 * @param lowerLocationList lowerLocationList
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportLocation(
			final List<InventoryLocationListForReport> locationList,
			final List<InventoryLowerLocationListForReport> lowerLocationList);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.（ロット在庫用）
	 * @param lotList lotList
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportLot(
			final List<InventoryLotListForReport> lotList);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.(全て用）
	 * @param locationList locationList
	 * @param lowerLocationList lowerLocationList
	 * @param lotList lotList
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportAll(
			final List<InventoryLocationListForReport> locationList,
			final List<InventoryLowerLocationListForReport> lowerLocationList,
			final List<InventoryLotListForReport> lotList);

}
