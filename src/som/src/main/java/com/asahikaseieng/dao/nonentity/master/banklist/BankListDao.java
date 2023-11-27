/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.banklist;

import java.util.List;

/**
 * BankListDaoクラス
 * @author t0011036
 */
public interface BankListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.banklist.BankList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<BankList>
	 */
	List<BankList> getList(final BankListPagerCondition condition);
}
