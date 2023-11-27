/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apbalanceforreport;

import java.util.List;

/**
 * ApBalanceListForReportDaoクラス
 * @author t0011036
 */
public interface ApBalanceListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * ApBalanceListForReportメソッド
	 * @param condition 検索条件
	 * @return List<ApBalanceListForReport>
	 */
	List<ApBalanceListForReport> getListForReport(
			final ApBalanceListConditionForReport condition);

	/** ARGSアノテーション getListTempForReport */
	String getListTempForReport_ARGS = "condition";

	/**
	 * ApBalanceListTempForReportメソッド
	 * @param condition 検索条件
	 * @return List<ApBalanceListForReport>
	 */
	List<ApBalanceListForReport> getListTempForReport(
			final ApBalanceListConditionForReport condition);
}
