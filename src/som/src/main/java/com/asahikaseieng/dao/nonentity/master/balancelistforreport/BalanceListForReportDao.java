/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelistforreport;

import java.util.List;

/**
 * BalanceListForReportDaoクラス
 * @author t0011036
 */
public interface BalanceListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.balancelistforreport.BalanceListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * BalanceListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<BalanceListForReport>
	 */
	List<BalanceListForReport> getListForReport(
			final BalanceListConditionForReport condition);
}
