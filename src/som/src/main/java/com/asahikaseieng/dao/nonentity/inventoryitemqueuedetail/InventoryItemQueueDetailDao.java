/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail;

/**
 * InventoryItemQueueDetailDaoクラス
 * @author t0011036
 */
public interface InventoryItemQueueDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail.InventoryItemQueueDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd";

	/**
	 * InventoryItemQueueDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @return InventoryItemQueueDetail
	 */
	InventoryItemQueueDetail getEntity(final String itemCd);
}
