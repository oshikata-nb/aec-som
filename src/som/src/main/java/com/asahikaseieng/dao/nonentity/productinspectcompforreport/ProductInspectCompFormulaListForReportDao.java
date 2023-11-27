/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productinspectcompforreport;

import java.util.List;

/**
 * ProductInspectCompFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface ProductInspectCompFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<ProductInspectCompFormulaListForReport>
	 */
	List<ProductInspectCompFormulaListForReport> getFormulaReportList(
			ProductInspectCompListConditionForReport condition);
}
