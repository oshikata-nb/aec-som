/*
 * Created on Fri Jan 23 13:40:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeheader;

/**
 * RecipeHeaderDaoインターフェース.
 * @author kanri-user
 */
public interface RecipeHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = RecipeHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipeHeader
	 * @return Insert件数
	 */
	int insert(RecipeHeader bean);

	/**
	 * Update.
	 * @param bean RecipeHeader
	 * @return Update件数
	 */
	int update(RecipeHeader bean);

	/**
	 * Delete.
	 * @param bean RecipeHeader
	 * @return Delete件数
	 */
	int delete(RecipeHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RECIPE_ID";

	/**
	 * エンティティ取得.
	 * @param recipeId recipeId
	 * @return RecipeHeader
	 */
	RecipeHeader getEntity(java.math.BigDecimal recipeId);
}
