/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemlist;

import java.util.List;

/**
 * ItemListDaoクラス
 * @author t0011036
 */
public interface ItemListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemlist.ItemList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemList>
	 */
	List<ItemList> getList(final ItemListPagerCondition condition);
}
