/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.util.List;

/**
 * 処方検査Daoインターフェース.
 *
 * @author tosco
 */
public interface DirectionRecipeInspectionListDao {

	/** BEANアノテーション */
	Class<DirectionRecipeInspectionList> BEAN = DirectionRecipeInspectionList.class;

	//
	// インスタンスメソッド
	//


	/** ARGSアノテーション getRecipeId(). */
	String getRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックス、で検索
	 * @param recipeId レシピインデックス
	 * @return List<DirectionRecipeInspectionList>
	 */
	List<DirectionRecipeInspectionList> getRecipeId(String recipeId);

}
