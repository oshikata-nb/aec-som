/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salestermsandestimate;

import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.販売条件&見積単価 コピー作成・削除処理管理テーブル一覧
 * @author t0011036
 */
public interface SalestermsAndEstimateListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final SalestermsAndEstimateListConditionForReport condition);
}
