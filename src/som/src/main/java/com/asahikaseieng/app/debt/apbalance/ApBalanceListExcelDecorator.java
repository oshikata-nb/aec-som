/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apbalance;

import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.買掛残高一覧
 * @author t0011036
 */
public interface ApBalanceListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final ApBalanceListConditionForReport condition);
}
