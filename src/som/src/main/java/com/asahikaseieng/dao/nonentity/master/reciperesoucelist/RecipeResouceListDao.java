/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucelist;

import java.util.List;

/**
 * RecipeResouceListDaoクラス
 * @author kanri-user
 */
public interface RecipeResouceListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reciperesoucelist.RecipeResouceList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<RecipeResouceList>
	 */
	List<RecipeResouceList> getList(
			final RecipeResouceListPagerCondition condition);
}
