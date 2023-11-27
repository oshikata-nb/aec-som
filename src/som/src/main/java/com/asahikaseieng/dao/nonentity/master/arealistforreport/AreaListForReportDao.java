/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.arealistforreport;

import java.util.List;

/**
 * AreaListForReportDaoクラス
 * @author t0011036
 */
public interface AreaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * AreaListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return AreaListForReport
	 */
	List<AreaListForReport> getListForReport(
			final AreaListConditionForReport condition);
}
