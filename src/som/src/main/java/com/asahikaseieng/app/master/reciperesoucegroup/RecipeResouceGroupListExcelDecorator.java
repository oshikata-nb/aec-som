/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.設備グループマスタ一覧
 * @author t0011036
 */
public interface RecipeResouceGroupListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final RecipeResouceGroupListConditionForReport condition);
}
