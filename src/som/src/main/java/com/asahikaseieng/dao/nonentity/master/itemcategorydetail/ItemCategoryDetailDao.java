/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemcategorydetail;

/**
 * ItemCategoryDetailDaoクラス
 * @author t0011036
 */
public interface ItemCategoryDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemcategorydetail.ItemCategoryDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCategory";

	/**
	 * ItemCategoryDetailメソッド
	 * 
	 * @param itemCategory itemCategory
	 * @return ItemCategoryDetail
	 */
	ItemCategoryDetail getEntity(final String itemCategory);
}
