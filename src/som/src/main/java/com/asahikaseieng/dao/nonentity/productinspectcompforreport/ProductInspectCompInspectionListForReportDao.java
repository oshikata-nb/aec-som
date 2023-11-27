/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productinspectcompforreport;

import java.util.List;

/**
 * ProductInspectCompInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface ProductInspectCompInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<ProductInspectCompInspectionListForReport>
	 */
	List<ProductInspectCompInspectionListForReport> getInspectionReportList(
			ProductInspectCompListConditionForReport condition);
}
