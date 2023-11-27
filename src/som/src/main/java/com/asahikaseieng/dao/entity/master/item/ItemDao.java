/*
 * Created on Thu Jan 22 17:06:28 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.item;

/**
 * ItemDaoインターフェース.
 * @author t0011036
 */
public interface ItemDao {

	/** BEANアノテーション. */
	Class BEAN = Item.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Item
	 * @return Insert件数
	 */
	int insert(Item bean);

	/**
	 * Update.
	 * @param bean Item
	 * @return Update件数
	 */
	int update(Item bean);

	/**
	 * Delete.
	 * @param bean Item
	 * @return Delete件数
	 */
	int delete(Item bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @return Item
	 */
	Item getEntity(final String itemCd);
}
