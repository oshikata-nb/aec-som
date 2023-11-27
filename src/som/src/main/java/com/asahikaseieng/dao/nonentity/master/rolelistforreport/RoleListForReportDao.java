/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelistforreport;

import java.util.List;

/**
 * RoleListForReportDaoクラス
 * @author t0011036
 */
public interface RoleListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * RoleListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<RoleListForReport>
	 */
	List<RoleListForReport> getListForReport(
			final RoleListConditionForReport condition);
}
