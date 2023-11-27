/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocklist;

import java.util.List;

/**
 * InventoryStockListDaoクラス
 * @author t0011036
 */
public interface InventoryStockListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryStockList>
	 */
	List<InventoryStockList> getList(
			final InventoryStockListPagerCondition condition);
}
