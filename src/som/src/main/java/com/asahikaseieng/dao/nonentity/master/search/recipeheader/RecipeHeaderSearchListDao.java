/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.master.search.recipeheader;

import java.util.List;

/**
 * 基本処方検索Popup－ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface RecipeHeaderSearchListDao {
	/** レシピタイプ－基本処方 */
	String RECIPE_TYPE_MASTER = "3";

	/** BEANアノテーション */
	Class<RecipeHeaderSearchList> BEAN = RecipeHeaderSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<RecipeHeader> 検索結果リスト
	 */
	List<RecipeHeaderSearchList> getSearchList(RecipeHeaderSearchListPagerCondition condition);

}
