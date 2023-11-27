/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.arrival;

import com.asahikaseieng.dao.nonentity.arrivalforreport.ArrivalListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.入荷一覧
 * @author t1344224
 */
public interface ArrivalReportListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrivalListConditionForReport condition);
}
