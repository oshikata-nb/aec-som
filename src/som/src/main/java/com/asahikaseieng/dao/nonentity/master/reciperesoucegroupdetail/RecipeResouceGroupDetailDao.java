/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail;

/**
 * RecipeResouceGroupDetailDaoクラス
 * @author t0011036
 */
public interface RecipeResouceGroupDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.
					RecipeResouceGroupDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "recipeResouceCd";

	/**
	 * RecipeResouceGroupDetailメソッド
	 * 
	 * @param recipeResouceCd recipeResouceCd
	 * @return RecipeResouceGroupDetail
	 */
	RecipeResouceGroupDetail getEntity(final String recipeResouceCd);
}
