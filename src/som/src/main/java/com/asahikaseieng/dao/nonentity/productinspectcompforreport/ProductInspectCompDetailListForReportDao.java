/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productinspectcompforreport;

import java.util.List;

/**
 * ProductInspectCompDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface ProductInspectCompDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<ProductInspectCompDetailListForReport>
	 */
	List<ProductInspectCompDetailListForReport> getDetailReportList(
			ProductInspectCompListConditionForReport condition);
}
