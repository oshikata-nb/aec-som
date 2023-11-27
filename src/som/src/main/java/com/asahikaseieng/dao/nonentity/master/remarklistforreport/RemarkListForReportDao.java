/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarklistforreport;

import java.util.List;

/**
 * RemarkListForReportDaoクラス
 * @author t0011036
 */
public interface RemarkListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * RemarkListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<RemarkListForReport>
	 */
	List<RemarkListForReport> getListForReport(
			final RemarkListConditionForReport condition);
}
