/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.品目分類マスタ一覧
 * @author t0011036
 */
public interface ItemCategoryListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final ItemCategoryListConditionForReport condition);
}
