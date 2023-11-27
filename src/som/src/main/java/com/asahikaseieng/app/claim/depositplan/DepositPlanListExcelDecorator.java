/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.depositplan;

import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.入金予定一覧
 * @author t0011036
 */
public interface DepositPlanListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final DepositPlanListConditionForReport condition);
}
