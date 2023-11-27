/*
 * Created on 2009/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylistforreport;

import java.util.List;

/**
 * InventoryLocationListForReportDaoクラス
 * @author kanri-user
 */
public interface InventoryLocationListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLocationListForReport.class;

	/** ARGSアノテーション getLocationListForReport */
	String getLocationListForReport_ARGS = "condition";

	/**
	 * InventoryLocationListForReportメソッド
	 * 
	 * @param condition condition
	 * @return InventoryLocationListForReport
	 */
	List<InventoryLocationListForReport> getLocationListForReport(
			final InventoryListForReportCondition condition);
}
