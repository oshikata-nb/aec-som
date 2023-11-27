/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist;

import java.util.List;

/**
 * RecipeResouceGroupListDaoクラス
 * @author t0011036
 */
public interface RecipeResouceGroupListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<RecipeResouceGroupList>
	 */
	List<RecipeResouceGroupList> getList(
			final RecipeResouceGroupListPagerCondition condition);
}
