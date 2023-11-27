/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.linelistforreport;

import java.util.List;

/**
 * LineListForReportDaoクラス
 * @author t0011036
 */
public interface LineListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.linelistforreport.LineListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * LineListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return LineListForReport
	 */
	List<LineListForReport> getListForReport(
			final LineListConditionForReport condition);
}
