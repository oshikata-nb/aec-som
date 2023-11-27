/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.midinspectcomp;

import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.帳票Excel
 * @author t1344224
 */
public interface MidInspectCompExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final MidInspectCompListConditionForReport condition);
}
