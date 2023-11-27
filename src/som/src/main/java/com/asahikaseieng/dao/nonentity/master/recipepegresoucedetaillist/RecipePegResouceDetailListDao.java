/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist;

import java.util.List;

/**
 * RecipePegResouceDetailListDaoクラス
 * @author t0011036
 */
public interface RecipePegResouceDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist.RecipePegResouceDetailList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "resouceCd";

	/**
	 * Listメソッド
	 * 
	 * @param resouceCd resouceCd
	 * @return List<RecipePegResouceDetailList>
	 */
	List<RecipePegResouceDetailList> getList(final String resouceCd);
}
