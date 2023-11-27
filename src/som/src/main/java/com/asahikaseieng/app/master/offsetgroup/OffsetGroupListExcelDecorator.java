/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.offsetgroup;

import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.相殺グループマスタ一覧
 * @author t0011036
 */
public interface OffsetGroupListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final OffsetGroupListConditionForReport condition);
}
