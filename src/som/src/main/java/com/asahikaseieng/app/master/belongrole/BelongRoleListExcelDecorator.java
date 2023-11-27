/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.所属・ロール組合せマスタ一覧
 * @author t0011036
 */
public interface BelongRoleListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final BelongRoleListConditionForReport condition);
}
