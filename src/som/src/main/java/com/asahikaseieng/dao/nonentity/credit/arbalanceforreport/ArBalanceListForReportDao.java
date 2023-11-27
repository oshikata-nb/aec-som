/*
 * Created on 2009/08/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalanceforreport;

import java.util.List;

/**
 * ArBalanceListForReportDaoクラス
 * @author t0011036
 */
public interface ArBalanceListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * ArBalanceListForReportメソッド
	 * @param condition 検索条件
	 * @return List<ArBalanceListForReport>
	 */
	List<ArBalanceListForReport> getListForReport(
			final ArBalanceListConditionForReport condition);
}
