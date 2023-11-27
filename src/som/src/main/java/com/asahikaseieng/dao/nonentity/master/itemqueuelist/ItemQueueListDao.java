/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelist;

import java.util.List;

/**
 * ItemQueueListDaoクラス
 * @author t0011036
 */
public interface ItemQueueListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueList.class;

	/** ARGSアノテーション getActivateList */
	String getActivateList_ARGS = "condition";

	/**
	 * getActivateListメソッド(未使用)
	 * 
	 * @param condition condition
	 * @return List<ItemQueueList>
	 */
	List<ItemQueueList> getActivateList(
			final ItemQueueListPagerCondition condition);

	/** ARGSアノテーション getInactivateList */
	String getInactivateList_ARGS = "condition";

	/**
	 * getInactivateListメソッド(未使用)
	 * 
	 * @param condition condition
	 * @return List<ItemQueueList>
	 */
	List<ItemQueueList> getInactivateList(
			final ItemQueueListPagerCondition condition);

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * getListメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueList>
	 */
	List<ItemQueueList> getList(final ItemQueueListPagerCondition condition);
}
