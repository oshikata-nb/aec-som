/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.util.List;

/**
 * 処方プロシージャDaoインターフェース.
 *
 * @author tosco
 */
public interface DirectionRecipeProcedureListDao {

	/** BEANアノテーション */
	Class<DirectionRecipeProcedureList> BEAN = DirectionRecipeProcedureList.class;

	//
	// インスタンスメソッド
	//
	/** ARGSアノテーション getRecipeId(). */
	String getRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックス、ステップNoで検索
	 * @param recipeId レシピインデックス
	 * @return List<DirectionRecipeProcedureList>
	 */
	List<DirectionRecipeProcedureList> getRecipeId(String recipeId);
}
