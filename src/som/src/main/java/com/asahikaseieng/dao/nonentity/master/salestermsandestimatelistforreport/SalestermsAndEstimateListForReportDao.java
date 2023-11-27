/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport;

import java.util.List;

/**
 * SalestermsAndEstimateListForReportDaoクラス
 * @author t0011036
 */
public interface SalestermsAndEstimateListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * SalestermsAndEstimateListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<SalestermsAndEstimateListForReport>
	 */
	List<SalestermsAndEstimateListForReport> getListForReport(
			final SalestermsAndEstimateListConditionForReport condition);
}
