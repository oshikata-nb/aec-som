
package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport;

import java.util.List;


public interface EstimateSavedListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.EstimateSavedListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "pkNo";

	/**
	 * SalestermsAndEstimateListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<SalestermsAndEstimateListForReport>
	 */
	List<EstimateSavedListForReport> getListForReport(final String pkNo);
}
