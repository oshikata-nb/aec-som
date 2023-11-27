
package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport;

import java.util.List;


public interface SalesTermsSavedListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalesTermsSavedListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "pkNo";

	/**
	 * SalestermsAndEstimateListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<SalestermsAndEstimateListForReport>
	 */
	List<SalesTermsSavedListForReport> getListForReport(final String pkNo);
}
