/*
 * Created on 2009/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongrolelistforreport;

import java.util.List;

/**
 * BelongRoleListForReportDaoクラス
 * @author t0011036
 */
public interface BelongRoleListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * BelongRoleListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<BelongRoleListForReport>
	 */
	List<BelongRoleListForReport> getListForReport(
			final BelongRoleListConditionForReport condition);
}
