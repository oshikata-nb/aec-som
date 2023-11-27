/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

import java.util.List;

/**
 * 処方その他用Daoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeRecipeRemarkListDao {

	/** BEANアノテーション */
	Class<GrecipeRecipeRemarkList> BEAN = GrecipeRecipeRemarkList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Reciperemark
	 * @return Insert件数
	 */
	int insert(GrecipeRecipeRemarkList bean);

	/**
	 * Update.
	 * @param bean Reciperemark
	 * @return Update件数
	 */
	int update(GrecipeRecipeRemarkList bean);

	/**
	 * Delete.
	 * @param bean Reciperemark
	 * @return Delete件数
	 */
	int delete(GrecipeRecipeRemarkList bean);

	/** ARGSアノテーション findByRecipeId(). */
	String findByRecipeId_ARGS = "recipeId";
	/**
	 * レシピIDに紐づくデータを取得
	 * @param recipeId レシピインデックス
	 * @return GrecipeRecipeRemarkList
	 */
	List<GrecipeRecipeRemarkList> findByRecipeId(String recipeId);

	/** ARGSアノテーション deleteByRecipeId(). */
	String deleteByRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @return Delete件数
	 */
	int deleteByRecipeId(String recipeId);

}
