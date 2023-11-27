/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrylist;

import java.util.List;

/**
 * CarryListDaoクラス
 * @author t0011036
 */
public interface CarryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.carrylist.CarryList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<CarryList>
	 */
	List<CarryList> getList(final CarryListPagerCondition condition);
}
