/*
 * Created on 2009/09/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyeportforreport;

import java.util.List;

/**
 * DailyReportDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface DailyReportDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportDetailListForReport.class;

	/** ARGSアノテーション getDetailListForReprot */
	String getDetailListForReprot_ARGS = "condition";

	/**
	 * DailyReportDetailListForReportメソッド
	 * @param condition condition
	 * @return DailyReportDetailListForReport
	 */
	List<DailyReportDetailListForReport> getDetailListForReprot(
			DailyReportListConditionForReport condition);
}
