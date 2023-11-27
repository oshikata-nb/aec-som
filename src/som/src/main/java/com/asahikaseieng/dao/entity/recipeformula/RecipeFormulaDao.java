/*
 * Created on Fri Jan 23 13:44:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeformula;

/**
 * RecipeFormulaDaoインターフェース.
 * @author kanri-user
 */
public interface RecipeFormulaDao {

	/** BEANアノテーション. */
	Class BEAN = RecipeFormula.class;

	//
	// インスタンスメソッド 
	//

	/**
	 * Insert.
	 * @param bean RecipeFormula
	 * @return Insert件数
	 */
	int insert(RecipeFormula bean);

	/**
	 * Update.
	 * @param bean RecipeFormula
	 * @return Update件数
	 */
	int update(RecipeFormula bean);

	/**
	 * Delete.
	 * @param bean RecipeFormula
	 * @return Delete件数
	 */
	int delete(RecipeFormula bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RECIPE_ID,STEP_NO,LINE_NO";

	/**
	 * エンティティ取得.
	 * @param recipeId recipeId
	 * @param stepNo stepNo
	 * @param lineNo lineNo
	 * @return RecipeFormula
	 */
	RecipeFormula getEntity(java.math.BigDecimal recipeId,
			java.math.BigDecimal stepNo, java.math.BigDecimal lineNo);
}
