/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountslist;

import java.util.List;

/**
 * AccountsDaoクラス
 * @author t0011036
 */
public interface AccountsListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.accountslist.AccountsList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<AccountsList>
	 */
	List<AccountsList> getList(final AccountsListPagerCondition condition);
}
