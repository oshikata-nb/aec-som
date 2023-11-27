/*
 * Created on 2009/09/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport;

import java.util.List;

/**
 * InventoryShippingoutListForReportDaoクラス
 * @author kanri-user
 */
public interface InventoryShippingoutListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport.InventoryShippingoutListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryShippingoutListForReport>
	 */
	List<InventoryShippingoutListForReport> getListForReport(
			InventoryShippingoutListConditionForReport condition);
}
