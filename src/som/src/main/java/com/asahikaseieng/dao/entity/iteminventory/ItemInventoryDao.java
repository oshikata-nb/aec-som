/*
 * Created on Thu Jan 22 18:23:14 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.iteminventory;

/**
 * ItemInventoryDaoインターフェース.
 * @author t0011036
 */
public interface ItemInventoryDao {

	/** BEANアノテーション. */
	Class BEAN = ItemInventory.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ItemInventory
	 * @return Insert件数
	 */
	int insert(ItemInventory bean);

	/**
	 * Update.
	 * @param bean ItemInventory
	 * @return Update件数
	 */
	int update(ItemInventory bean);

	/**
	 * Delete.
	 * @param bean ItemInventory
	 * @return Delete件数
	 */
	int delete(ItemInventory bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @return ItemInventory
	 */
	ItemInventory getEntity(final String itemCd);
}
