/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrylistforreport;

import java.util.List;

/**
 * CarryListForReportDaoクラス
 * @author t0011036
 */
public interface CarryListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * CarryListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return CarryListForReport
	 */
	List<CarryListForReport> getListForReport(
			final CarryListConditionForReport condition);
}
