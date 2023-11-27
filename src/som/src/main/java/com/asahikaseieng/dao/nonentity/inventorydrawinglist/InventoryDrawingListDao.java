/*
 * Created on 2009/04/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawinglist;

import java.util.List;

/**
 * InventoryDrawingListDaoクラス
 * @author t0011036
 */
public interface InventoryDrawingListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<InventoryDrawingList>
	 */
	List<InventoryDrawingList> getList(
			final InventoryDrawingListPagerCondition condition);
}
