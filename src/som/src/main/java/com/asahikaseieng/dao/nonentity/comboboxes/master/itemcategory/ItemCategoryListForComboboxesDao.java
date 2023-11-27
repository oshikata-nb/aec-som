/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory;

import java.util.List;

/**
 * ItemCategoryListForComboboxesDaoクラス
 * @author t0011036
 */
public interface ItemCategoryListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory.ItemCategoryListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * ItemCategoryListForComboboxesメソッド
	 * 
	 * @return List<ItemCategoryListForComboboxes>
	 */
	List<ItemCategoryListForComboboxes> getListForComboboxes();
}
