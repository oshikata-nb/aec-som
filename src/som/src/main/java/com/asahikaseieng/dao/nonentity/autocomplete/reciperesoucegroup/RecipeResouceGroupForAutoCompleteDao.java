/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup;

import java.util.List;

/**
 * RecipeResouceGroupForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface RecipeResouceGroupForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup.RecipeResouceGroupForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "resouceGroupCd,rowlimit";

	/**
	 * RecipeResouceGroupForAutoCompleteメソッド
	 * 
	 * @param resouceGroupCd resouceGroupCd
	 * @param rowlimit 行上限
	 * @return RecipeResouceGroupForAutoComplete
	 */
	List<RecipeResouceGroupForAutoComplete> getSearchList(
			final String resouceGroupCd, final String rowlimit);
}
