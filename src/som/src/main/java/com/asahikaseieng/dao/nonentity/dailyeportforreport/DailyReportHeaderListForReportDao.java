/*
 * Created on 2009/09/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyeportforreport;

import java.util.List;

/**
 * DailyReportHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface DailyReportHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportHeaderListForReport.class;

	/** ARGSアノテーション getHeaderListForReprot */
	String getHeaderListForReprot_ARGS = "condition";

	/**
	 * DailyReportHeaderListForReportメソッド
	 * @param condition condition]
	 * @return DailyReportHeaderListForReport
	 */
	List<DailyReportHeaderListForReport> getHeaderListForReprot(
			DailyReportListConditionForReport condition);
}
