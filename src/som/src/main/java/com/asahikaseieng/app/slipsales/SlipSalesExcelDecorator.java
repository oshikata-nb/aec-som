/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipsales;

import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.売上伝票一覧
 * @author t1344224
 */
public interface SlipSalesExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final SlipSalesListConditionForReport condition);
}
