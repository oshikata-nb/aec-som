/*
 * Created on 2009/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylistforreport;

import java.util.List;

/**
 * InventoryLotListForReportDaoクラス
 * @author kanri-user
 */
public interface InventoryLotListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLotListForReport.class;

	/** ARGSアノテーション getLotListForReport */
	String getLotListForReport_ARGS = "condition";

	/**
	 * InventoryLotListForReportメソッド
	 * @param condition condition
	 * @return InventoryLotListForReport
	 */
	List<InventoryLotListForReport> getLotListForReport(
			final InventoryListForReportCondition condition);
}
