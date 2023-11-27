/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.callist;

import java.util.List;

/**
 * CalListDaoクラス
 * @author t0011036
 */
public interface CalListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.callist.CalList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<CalList>
	 */
	List<CalList> getList(final CalListPagerCondition condition);
}
