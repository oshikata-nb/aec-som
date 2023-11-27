/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldelete;

/**
 * CalDeleteDaoクラス
 * @author t0011036
 */
public interface CalDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.caldelete.CalDelete.class;

	/** ARGSアノテーション deleteList */
	String deleteList_ARGS = "calCd,calYear";

	/**
	 * deleteListメソッド
	 * 
	 * @param calCd calCd
	 * @param calYear calYear
	 * @return int
	 */
	int deleteList(final String calCd, final String calYear);
}
