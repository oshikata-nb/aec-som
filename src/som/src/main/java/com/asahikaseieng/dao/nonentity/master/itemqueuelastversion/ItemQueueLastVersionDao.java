/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelastversion;

/**
 * ItemQueueLastVersionDaoクラス
 * @author t0011036
 */
public interface ItemQueueLastVersionDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion.class;

	/** ARGSアノテーション getLastVersion */
	String getLastVersion_ARGS = "itemCd";

	/**
	 * ItemQueueLastVersionメソッド
	 * 
	 * @param itemCd itemCd
	 * @return ItemQueueLastVersion
	 */
	ItemQueueLastVersion getLastVersion(final String itemCd);
}
