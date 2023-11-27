/*
 * Created on 2009/07/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelistforreport;

import java.util.List;

/**
 * EstimateListForReportDaoクラス
 * @author kanri-user
 */
public interface EstimateListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * EstimateListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<EstimateListForReport>
	 */
	List<EstimateListForReport> getListForReport(
			final EstimateListConditionForReport condition);
}
