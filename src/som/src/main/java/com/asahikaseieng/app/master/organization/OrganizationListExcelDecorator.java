/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.部署マスタ一覧
 * @author t0011036
 */
public interface OrganizationListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final OrganizationListConditionForReport condition);
}
