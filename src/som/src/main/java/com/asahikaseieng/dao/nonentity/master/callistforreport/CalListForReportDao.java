/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.callistforreport;

import java.util.List;

/**
 * CalListForReportDaoクラス
 * @author t0011036
 */
public interface CalListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.callistforreport.CalListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * CalListForReportメソッド
	 * @param condition 検索条件
	 * @return List<CalListForReport>
	 */
	List<CalListForReport> getListForReport(
			final CalListConditionForReport condition);
}
