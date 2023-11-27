/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchaseorderforreport;

import java.util.List;

/**
 * PurchaseOrderListForReporDaoクラス
 * @author kanri-user
 */
public interface PurchaseOrderListForReporDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListForRepor.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<PurchaseOrderListForRepor> getReportList(
			PurchaseOrderListConditionForRepor condition);
}
