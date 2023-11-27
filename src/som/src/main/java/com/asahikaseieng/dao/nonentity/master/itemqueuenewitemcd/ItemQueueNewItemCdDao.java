/*
 * Created on 2009/09/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuenewitemcd;


/**
 * ItemQueueNewItemCdDaoクラス
 * @author t0011036
 */
public interface ItemQueueNewItemCdDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemqueuenewitemcd.ItemQueueNewItemCd.class;

	/** ARGSアノテーション getNewItemCd */
	String getNewItemCd_ARGS = "";

	/**
	 * ItemQueueNewItemCdメソッド
	 *
	 * @return ItemQueueNewItemCd
	 */
	ItemQueueNewItemCd getNewItemCd(
	);
}
