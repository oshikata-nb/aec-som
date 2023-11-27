/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arbalance;

import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.地区マスタ一覧
 * @author t0011036
 */
public interface ArBalanceListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final ArBalanceListConditionForReport condition);
}
