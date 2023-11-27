/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.製造指図一覧一覧
 * @author t1344224
 */
public interface DirectionOrderListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final DirectionOrderListConditionForReport condition);
}
