/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutlist;

import java.util.List;

/**
 * InventoryShippingoutListDaoクラス
 * @author t0011036
 */
public interface InventoryShippingoutListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventoryshippingoutlist.InventoryShippingoutList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryShippingoutList>
	 */
	List<InventoryShippingoutList> getList(
			final InventoryShippingoutListPagerCondition condition);
}
