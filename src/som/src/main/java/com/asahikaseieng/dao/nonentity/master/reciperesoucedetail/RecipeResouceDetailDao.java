/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucedetail;

/**
 * RecipeResouceDetailDaoクラス
 * @author kanri-user
 */
public interface RecipeResouceDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reciperesoucedetail.RecipeResouceDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "resouceCd";

	/**
	 * RecipeResouceDetailメソッド
	 * 
	 * @param resouceCd resouceCd
	 * @return RecipeResouceDetail
	 */
	RecipeResouceDetail getEntity(final String resouceCd);
}
