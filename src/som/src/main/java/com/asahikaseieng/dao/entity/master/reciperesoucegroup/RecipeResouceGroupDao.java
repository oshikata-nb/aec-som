/*
 * Created on Fri Feb 20 18:07:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.reciperesoucegroup;

/**
 * RecipeResouceGroupDaoインターフェース.
 * @author kanri-user
 */
public interface RecipeResouceGroupDao {

	/** BEANアノテーション. */
	Class BEAN = RecipeResouceGroup.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipeResouceGroup
	 * @return Insert件数
	 */
	int insert(RecipeResouceGroup bean);

	/**
	 * Update.
	 * @param bean RecipeResouceGroup
	 * @return Update件数
	 */
	int update(RecipeResouceGroup bean);

	/**
	 * Delete.
	 * @param bean RecipeResouceGroup
	 * @return Delete件数
	 */
	int delete(RecipeResouceGroup bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RESOUCE_GROUP_CD";

	/**
	 * エンティティ取得.
	 * @param resouceGroupCd resouceGroupCd
	 * @return RecipeResouceGroup
	 */
	RecipeResouceGroup getEntity(String resouceGroupCd);
}
