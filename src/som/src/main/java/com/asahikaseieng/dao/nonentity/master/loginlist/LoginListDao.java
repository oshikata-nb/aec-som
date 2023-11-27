/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlist;

import java.util.List;

/**
 * LoginListDaoクラス
 * @author t0011036
 */
public interface LoginListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.loginlist.LoginList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<LoginList>
	 */
	List<LoginList> getList(final LoginListPagerCondition condition);
}
