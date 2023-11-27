/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.accounts;

import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.勘定科目マスタ一覧
 * @author t0011036
 */
public interface AccountsListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final AccountsListConditionForReport condition);
}
