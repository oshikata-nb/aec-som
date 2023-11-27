/*
 * Created on Fri Jan 23 13:32:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeinspection;

/**
 * RecipeInspectionDaoインターフェース.
 * @author kanri-user
 */
public interface RecipeInspectionDao {

	/** BEANアノテーション. */
	Class BEAN = RecipeInspection.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipeInspection
	 * @return Insert件数
	 */
	int insert(RecipeInspection bean);

	/**
	 * Update.
	 * @param bean RecipeInspection
	 * @return Update件数
	 */
	int update(RecipeInspection bean);

	/**
	 * Delete.
	 * @param bean RecipeInspection
	 * @return Delete件数
	 */
	int delete(RecipeInspection bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RECIPE_ID,STEP_NO,LINE_NO";

	/**
	 * エンティティ取得.
	 * @param recipeId recipeId
	 * @param stepNo stepNo
	 * @param lineNo lineNo
	 * @return RecipeInspection
	 */
	RecipeInspection getEntity(java.math.BigDecimal recipeId,
			java.math.BigDecimal stepNo, java.math.BigDecimal lineNo);
}
