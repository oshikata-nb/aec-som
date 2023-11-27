/*
 * Created on 2009/08/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositplanforreport;

import java.util.List;

/**
 * DepositPlanListForReportDaoクラス
 * @author t0011036
 */
public interface DepositPlanListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * DepositPlanListForReportメソッド
	 * @param condition 検索条件
	 * @return List<DepositPlanListForReport>
	 */
	List<DepositPlanListForReport> getListForReport(
			final DepositPlanListConditionForReport condition);
}
