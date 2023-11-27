/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemcategorylist;

import java.util.List;

/**
 * ItemCategoryListDaoクラス
 * @author t0011036
 */
public interface ItemCategoryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemCategoryList>
	 */
	List<ItemCategoryList> getList(
			final ItemCategoryListPagerCondition condition);
}
