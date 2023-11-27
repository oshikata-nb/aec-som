/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.在庫移動入力一覧
 * @author t0011036
 */
public interface InventoryMoveListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param list list
	 * @param tantoNm tantoNm
	 * @param currentDate currentDate
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final List<InventoryMoveListForReport> list,
			final String tantoNm, final Timestamp currentDate);
}
