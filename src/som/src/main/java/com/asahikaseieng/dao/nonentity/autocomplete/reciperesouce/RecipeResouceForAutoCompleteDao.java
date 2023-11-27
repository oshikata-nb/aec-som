/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce;

import java.util.List;

/**
 * RecipeResouceForAutoCompleteDaoクラス
 * @author kanri-user
 */
public interface RecipeResouceForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.RecipeResouceForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "resouceCd,rowlimit";

	/**
	 * RecipeResouceForAutoCompleteメソッド
	 * 
	 * @param resouceCd resouceCd
	 * @param rowlimit 行上限
	 * @return List<RecipeResouceForAutoComplete>
	 */
	List<RecipeResouceForAutoComplete> getSearchList(final String resouceCd,
			final String rowlimit);
}
