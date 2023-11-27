/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclasslistforreport;

import java.util.List;

/**
 * FinancialClassListForReportDaoクラス
 * @author t0011036
 */
public interface FinancialClassListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * FinancialClassListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<FinancialClassListForReport>
	 */
	List<FinancialClassListForReport> getListForReport(
			final FinancialClassListConditionForReport condition);
}
