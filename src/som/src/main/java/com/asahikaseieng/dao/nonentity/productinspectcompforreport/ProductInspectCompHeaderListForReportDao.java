/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productinspectcompforreport;

import java.util.List;

/**
 * ProductInspectCompHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface ProductInspectCompHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompHeaderListForReport.class;

	/** ARGSアノテーション getHeaderReportList */
	String getHeaderReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition v
	 * @return List
	 */
	List<ProductInspectCompHeaderListForReport> getHeaderReportList(
			ProductInspectCompListConditionForReport condition);
}
