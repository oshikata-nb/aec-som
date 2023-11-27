/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.materialrinput;

import com.asahikaseieng.dao.nonentity.materialrinputforreport.MaterialRinputListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.外注原材料投入実績一覧
 * @author t1344224
 */
public interface MaterialRinputListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final MaterialRinputListConditionForReport condition);
}
