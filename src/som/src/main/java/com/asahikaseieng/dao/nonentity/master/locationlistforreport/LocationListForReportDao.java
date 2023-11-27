/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlistforreport;

import java.util.List;

/**
 * LocationListForReportDaoクラス
 * @author t0011036
 */
public interface LocationListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.locationlistforreport.LocationListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * LocationListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return LocationListForReport
	 */
	List<LocationListForReport> getListForReport(
			final LocationListConditionForReport condition);
}
