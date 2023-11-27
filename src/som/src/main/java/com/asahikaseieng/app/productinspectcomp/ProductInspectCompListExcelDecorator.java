/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.productinspectcomp;

import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.製品検査完了入力一覧
 * @author t1344224
 */
public interface ProductInspectCompListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final ProductInspectCompListConditionForReport condition);
}
