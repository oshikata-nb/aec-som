/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productinspectcompforreport;

import java.util.List;

/**
 * ProductInspectCompProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface ProductInspectCompProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<ProductInspectCompProcedureListForReport>
	 */
	List<ProductInspectCompProcedureListForReport> getProcedureReportList(
			ProductInspectCompListConditionForReport condition);
}
