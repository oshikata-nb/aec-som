/*
 * Created on Mon Feb 23 15:19:49 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.reciperesouce;

/**
 * RecipeResouceDaoインターフェース.
 * @author kanri-user
 */
public interface RecipeResouceDao {

	/** BEANアノテーション. */
	Class BEAN = RecipeResouce.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipeResouce
	 * @return Insert件数
	 */
	int insert(RecipeResouce bean);

	/**
	 * Update.
	 * @param bean RecipeResouce
	 * @return Update件数
	 */
	int update(RecipeResouce bean);

	/**
	 * Delete.
	 * @param bean RecipeResouce
	 * @return Delete件数
	 */
	int delete(RecipeResouce bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RESOUCE_CD";

	/**
	 * エンティティ取得.
	 * @param resouceCd resouceCd
	 * @return RecipeResouce
	 */
	RecipeResouce getEntity(String resouceCd);
}
