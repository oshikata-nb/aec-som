/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelist;

import java.util.List;

/**
 * BalanceListDaoクラス
 * @author t0011036
 */
public interface BalanceListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.balancelist.BalanceList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<BalanceList>
	 */
	List<BalanceList> getList(final BalanceListPagerCondition condition);
}
