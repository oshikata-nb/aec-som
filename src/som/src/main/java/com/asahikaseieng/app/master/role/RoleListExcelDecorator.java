/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.ロールマスタ一覧
 * @author t0011036
 */
public interface RoleListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final RoleListConditionForReport condition);
}
