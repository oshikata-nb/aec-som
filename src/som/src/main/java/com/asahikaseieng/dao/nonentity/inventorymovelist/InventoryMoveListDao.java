/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovelist;

import java.util.List;

/**
 * InventoryMoveListDaoクラス
 * @author t0011036
 */
public interface InventoryMoveListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorymovelist.InventoryMoveList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<InventoryMoveList>
	 */
	List<InventoryMoveList> getList(
			final InventoryMoveListPagerCondition condition);
}
