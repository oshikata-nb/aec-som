/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.itemcategory;

import java.util.List;

/**
 * ItemCategoryForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface ItemCategoryForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.itemcategory.ItemCategoryForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "itemCategory,rowlimit";

	/**
	 * ItemCategoryForAutoCompleteメソッド
	 * 
	 * @param itemCategory itemCategory
	 * @param rowlimit 行上限
	 * @return List<ItemCategoryForAutoComplete>
	 */
	List<ItemCategoryForAutoComplete> getSearchList(final String itemCategory,
			final String rowlimit);
}
