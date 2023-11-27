/*
 * Created on Thu Jan 22 17:18:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemcategory;

/**
 * ItemCategoryDaoインターフェース.
 * @author t0011036
 */
public interface ItemCategoryDao {

	/** BEANアノテーション. */
	Class BEAN = ItemCategory.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ItemCategory
	 * @return Insert件数
	 */
	int insert(ItemCategory bean);

	/**
	 * Update.
	 * @param bean ItemCategory
	 * @return Update件数
	 */
	int update(ItemCategory bean);

	/**
	 * Delete.
	 * @param bean ItemCategory
	 * @return Delete件数
	 */
	int delete(ItemCategory bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CATEGORY";

	/**
	 * エンティティ取得.
	 * @param itemCategory itemCategory
	 * @return ItemCategory
	 */
	ItemCategory getEntity(final String itemCategory);
}
