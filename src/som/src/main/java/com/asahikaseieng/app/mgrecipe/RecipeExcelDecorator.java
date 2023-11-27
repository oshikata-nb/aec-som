/*
 * Created on 2009/05/05
 
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeReportConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 処方ＥＸＣＥＬファイル作成用 interface.
 * @author ｔ１３４４２２４
 */
public interface RecipeExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final RecipeReportConditionForReport condition);
}
