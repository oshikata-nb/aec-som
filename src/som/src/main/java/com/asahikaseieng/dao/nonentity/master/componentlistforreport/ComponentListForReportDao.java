/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlistforreport;

import java.util.List;

/**
 * ComponentListForReportDaoクラス
 * @author t0011036
 */
public interface ComponentListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * ComponentListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return ComponentListForReport
	 */
	List<ComponentListForReport> getListForReport(
			final ComponentListConditionForReport condition);
}
