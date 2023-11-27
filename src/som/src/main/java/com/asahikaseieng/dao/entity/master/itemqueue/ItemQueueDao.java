/*
 * Created on Thu Jan 22 16:04:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemqueue;

/**
 * ItemQueueDaoインターフェース.
 * @author t0011036
 */
public interface ItemQueueDao {

	/** BEANアノテーション. */
	Class BEAN = ItemQueue.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ItemQueue
	 * @return Insert件数
	 */
	int insert(ItemQueue bean);

	/**
	 * Update.
	 * @param bean ItemQueue
	 * @return Update件数
	 */
	int update(ItemQueue bean);

	/**
	 * Delete.
	 * @param bean ItemQueue
	 * @return Delete件数
	 */
	int delete(ItemQueue bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,VERSION";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param version version
	 * @return ItemQueue
	 */
	ItemQueue getEntity(final String itemCd, final java.math.BigDecimal version);
}
