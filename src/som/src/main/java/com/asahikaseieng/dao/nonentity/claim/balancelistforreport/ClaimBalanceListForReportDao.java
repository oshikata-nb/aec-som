/*
 * Created on 2009/08/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.balancelistforreport;

import java.util.List;

/**
 * ClaimBalanceListForReportDaoクラス
 * @author t0011036
 */
public interface ClaimBalanceListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * ClaimBalanceListForReportメソッド
	 * @param condition 検索条件
	 * @return List<ClaimBalanceListForReport>
	 */
	List<ClaimBalanceListForReport> getListForReport(
			final ClaimBalanceListConditionForReport condition);
}
