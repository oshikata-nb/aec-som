/*
 * Created on Fri Jan 23 13:26:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeprocedure;

/**
 * RecipeProcedureDaoインターフェース.
 * @author kanri-user
 */
public interface RecipeProcedureDao {

	/** BEANアノテーション. */
	Class BEAN = RecipeProcedure.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipeProcedure
	 * @return Insert件数
	 */
	int insert(RecipeProcedure bean);

	/**
	 * Update.
	 * @param bean RecipeProcedure
	 * @return Update件数
	 */
	int update(RecipeProcedure bean);

	/**
	 * Delete.
	 * @param bean RecipeProcedure
	 * @return Delete件数
	 */
	int delete(RecipeProcedure bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RECIPE_ID,STEP_NO";

	/**
	 * エンティティ取得.
	 * @param recipeId recipeId
	 * @param stepNo stepNo
	 * @return RecipeProcedure
	 */
	RecipeProcedure getEntity(java.math.BigDecimal recipeId,
			java.math.BigDecimal stepNo);
}
