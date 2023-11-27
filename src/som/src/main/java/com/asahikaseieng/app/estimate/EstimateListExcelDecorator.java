/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.見積/単価マスタ一覧
 * @author t0011036
 */
public interface EstimateListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final EstimateListConditionForReport condition);
}
