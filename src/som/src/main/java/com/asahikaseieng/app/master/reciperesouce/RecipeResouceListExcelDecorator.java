/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.設備マスタ一覧
 * @author t0011036
 */
public interface RecipeResouceListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final RecipeResouceListConditionForReport condition);
}
