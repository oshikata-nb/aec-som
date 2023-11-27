/*
 * Created on 2009/09/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovelistforreport;

import java.util.List;

/**
 * InventoryMoveListForReportDaoクラス
 * @author kanri-user
 */
public interface InventoryMoveListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryMoveListForReport>
	 */
	List<InventoryMoveListForReport> getListForReport(
			InventoryMoveListConditionForReport condition);
}
