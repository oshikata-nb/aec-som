/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasonlistforreport;

import java.util.List;

/**
 * ReasonListForReportDaoクラス
 * @author t0011036
 */
public interface ReasonListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * ReasonListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<ReasonListForReport>
	 */
	List<ReasonListForReport> getListForReport(
			final ReasonListConditionForReport condition);
}
