/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

/**
 * 処方その他用Daoインターフェース.
 *
 * @author tosco
 */
public interface RecipeRemarkListDao {

	/** BEANアノテーション */
	Class<RecipeRemarkList> BEAN = com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Reciperemark
	 * @return Insert件数
	 */
	int insert(RecipeRemarkList bean);

	/**
	 * Update.
	 * @param bean Reciperemark
	 * @return Update件数
	 */
	int update(RecipeRemarkList bean);

	/**
	 * Delete.
	 * @param bean Reciperemark
	 * @return Delete件数
	 */
	int delete(RecipeRemarkList bean);

	/** ARGSアノテーション findByRecipeId(). */
	String findByRecipeId_ARGS = "recipeId";
	/**
	 * レシピIDに紐づくデータを取得
	 * @param recipeId レシピインデックス
	 * @return RecipeRemarkList
	 */
	List<RecipeRemarkList> findByRecipeId(String recipeId);

	/** ARGSアノテーション findByRecipeId(). */
	String getByRecipeId_ARGS = "recipeId";
	/**
	 * レシピIDに紐づくデータを取得 2009/2/20
	 * @param recipeId レシピインデックス
	 * @return RecipeRemarkList
	 */
	RecipeRemarkList getByRecipeId(BigDecimal recipeId);


	/** ARGSアノテーション deleteByRecipeId(). */
	String deleteByRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @return Delete件数
	 */
	int deleteByRecipeId(String recipeId);

//	/** ARGSアノテーション getEntity(). */
//	String getEntity_ARGS = "RECIPE_ID";
//
//	/**
//	 * エンティティ取得.
//	 * @param recipeId recipeId
//	 * @return Reciperemark
//	 */
//	RecipeRemarkList getEntity(String recipeId);

}
