/*
 * Created on 2009/09/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productionforreport;

import java.util.List;

/**
 * ProductionHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface ProductionHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.productionforreport.ProductionHeaderListForReport.class;

	/** ARGSアノテーション getHeaderListForReport */
	String getHeaderListForReport_ARGS = "condition";

	/**
	 * ProductionHeaderListForReportメソッド
	 * @param condition condition
	 * @return ProductionHeaderListForReport
	 */
	List<ProductionHeaderListForReport> getHeaderListForReport(
			ProductionListConditionForReport condition);
}
