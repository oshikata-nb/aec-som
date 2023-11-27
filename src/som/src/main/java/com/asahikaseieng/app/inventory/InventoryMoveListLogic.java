/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventorymovelist.InventoryMoveList;
import com.asahikaseieng.dao.nonentity.inventorymovelist.InventoryMoveListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListConditionForReport;
import com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 在庫移動入力一覧ロジック interface.
 * @author FPC
 */
public interface InventoryMoveListLogic {
	/**
	 * 検索処理を行う．
	 * @param condition condition
	 * @return List<InventoryMoveList>
	 * @exception NoDataException NoDataException
	 */
	List<InventoryMoveList> getList(
			final InventoryMoveListPagerCondition condition)
			throws NoDataException;

	/**
	 * 在庫移動入力一覧検索（帳票用）
	 * @param condition condition
	 * @return List<InventoryMoveListForReport>
	 */
	List<InventoryMoveListForReport> getListForReport(
			final InventoryMoveListConditionForReport condition);
}
