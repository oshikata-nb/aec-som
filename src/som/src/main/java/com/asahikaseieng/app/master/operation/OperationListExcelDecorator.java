/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.operation;

import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.工程マスタ一覧
 * @author t0011036
 */
public interface OperationListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final OperationListConditionForReport condition);
}
