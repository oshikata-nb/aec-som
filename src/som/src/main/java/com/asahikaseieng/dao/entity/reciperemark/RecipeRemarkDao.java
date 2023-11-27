/*
 * Created on Fri Jan 23 10:55:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.reciperemark;
import java.util.List;

/**
 * RecipeRemarkDaoインターフェース.
 * @author kanri-user
 */
public interface RecipeRemarkDao {

	/** BEANアノテーション. */
	Class BEAN = RecipeRemark.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipeRemark
	 * @return Insert件数
	 */
	int insert(RecipeRemark bean);

	/**
	 * Update.
	 * @param bean RecipeRemark
	 * @return Update件数
	 */
	int update(RecipeRemark bean);

	/**
	 * Delete.
	 * @param bean RecipeRemark
	 * @return Delete件数
	 */
	int delete(RecipeRemark bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RECIPE_ID";

	/**
	 * エンティティ取得.
	 * @param recipeId recipeId
	 * @return RecipeRemark
	 */
	RecipeRemark getEntity(java.math.BigDecimal recipeId);

	/**
	 * リスト取得.
	 * @return RecipeRemarkのリスト
	 */
	List<RecipeRemark> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

