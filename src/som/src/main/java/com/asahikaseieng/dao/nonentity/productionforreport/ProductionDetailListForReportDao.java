/*
 * Created on 2009/09/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productionforreport;

import java.util.List;

/**
 * ProductionDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface ProductionDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.productionforreport.ProductionDetailListForReport.class;

	/** ARGSアノテーション getDetailListForReport */
	String getDetailListForReport_ARGS = "condition";

	/**
	 * ProductionDetailListForReportメソッド
	 * @param condition condition
	 * @return ProductionDetailListForReport
	 */
	List<ProductionDetailListForReport> getDetailListForReport(
			ProductionListConditionForReport condition);
}
