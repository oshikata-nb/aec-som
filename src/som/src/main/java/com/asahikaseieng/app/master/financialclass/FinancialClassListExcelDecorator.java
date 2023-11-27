/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.financialclass;

import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.財務分類マスタ一覧
 * @author t0011036
 */
public interface FinancialClassListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final FinancialClassListConditionForReport condition);
}
