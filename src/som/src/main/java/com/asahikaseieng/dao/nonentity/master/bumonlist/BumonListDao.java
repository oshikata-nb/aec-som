/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlist;

import java.util.List;

/**
 * BumonListDaoクラス
 * @author t0011036
 */
public interface BumonListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.bumonlist.BumonList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<BumonList>
	 */
	List<BumonList> getList(final BumonListPagerCondition condition);
}
