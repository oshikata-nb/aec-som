/*
 * Created on Fri Feb 20 17:24:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeasprova;

/**
 * RecipeAsprovaDaoインターフェース.
 * @author kanri-user
 */
public interface RecipeAsprovaDao {

	/** BEANアノテーション. */
	Class BEAN = RecipeAsprova.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipeAsprova
	 * @return Insert件数
	 */
	int insert(RecipeAsprova bean);

	/**
	 * Update.
	 * @param bean RecipeAsprova
	 * @return Update件数
	 */
	int update(RecipeAsprova bean);

	/**
	 * Delete.
	 * @param bean RecipeAsprova
	 * @return Delete件数
	 */
	int delete(RecipeAsprova bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OPERATION_GROUP_CD,RECIPE_ID,RESOUCE_CD,RESOUCE_GROUP_CD";

	/**
	 * エンティティ取得.
	 * @param operationGroupCd operationGroupCd
	 * @param recipeId recipeId
	 * @param resouceCd resouceCd
	 * @param resouceGroupCd resouceGroupCd
	 * @return RecipeAsprova
	 */
	RecipeAsprova getEntity(String operationGroupCd,
			java.math.BigDecimal recipeId, String resouceCd,
			String resouceGroupCd);

}
