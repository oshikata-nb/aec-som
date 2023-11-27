/*
 * Created on 2009/05/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelastactive;

/**
 * ItemQueueLastActiveDaoクラス
 * @author t0011036
 */
public interface ItemQueueLastActiveDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemqueuelastactive.ItemQueueLastActive.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd";

	/**
	 * ItemQueueLastActiveメソッド
	 * 
	 * @param itemCd itemCd
	 * @return ItemQueueLastActive
	 */
	ItemQueueLastActive getEntity(final String itemCd);
}
