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
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.
 * @author FPC
 */
public interface InventoryStockListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param list list
	 * @param tantoName tantoName
	 * @param currentDate currentDate
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final List<InventoryStockListForReport> list,
			final String tantoName, final Timestamp currentDate);
}
