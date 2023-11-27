/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.recipepegresoucedelete;

/**
 * RecipePegResouceDeleteDaoクラス
 * @author t0011036
 */
public interface RecipePegResouceDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.recipepegresoucedelete.RecipePegResouceDelete.class;

	/** ARGSアノテーション deleteList */
	String deleteList_ARGS = "resouceCd";

	/**
	 * deleteListメソッド
	 * 
	 * @param resouceCd resouceCd
	 * @return int
	 */
	int deleteList(final String resouceCd);
}
