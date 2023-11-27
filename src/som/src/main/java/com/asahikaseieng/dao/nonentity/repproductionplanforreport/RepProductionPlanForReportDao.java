/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repproductionplanforreport;

import java.util.List;

/**
 * RepProductionPlanForReportDaoクラス
 * @author kanri-user
 */
public interface RepProductionPlanForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReport.class;

	/** ARGSアノテーション getProductionPlanListForReport */
	String getProductionPlanListForReport_ARGS = "condition";

	/**
	 * RepProductionPlanForReportメソッド
	 * 
	 * @param condition condition
	 * @return RepProductionPlanForReport
	 */
	List<RepProductionPlanForReport> getProductionPlanListForReport(
			final RepProductionPlanForReportCondition condition);
}
