/*
 * Created on 2009/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylistforreport;

import java.util.List;

/**
 * InventoryLowerLocationListForReportDaoクラス
 * @author kanri-user
 */
public interface InventoryLowerLocationListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLowerLocationListForReport.class;

	/** ARGSアノテーション getLowerLocationListForReport */
	String getLowerLocationListForReport_ARGS = "condition";

	/**
	 * InventoryLowerLocationListForReportメソッド
	 * @param condition condition
	 * @return InventoryLowerLocationListForReport
	 */
	List<InventoryLowerLocationListForReport> getLowerLocationListForReport(
			final InventoryListForReportCondition condition);
}
