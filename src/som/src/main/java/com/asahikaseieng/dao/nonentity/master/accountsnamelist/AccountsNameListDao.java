/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountsnamelist;

import java.util.List;

/**
 * AccountsNameListDaoクラス
 * @author t0011036
 */
public interface AccountsNameListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.accountsnamelist.AccountsNameList.class;

	/** ARGSアノテーション getNameList */
	String getNameList_ARGS = "accountsCd";

	/**
	 * Listメソッド
	 * 
	 * @param accountsCd accountsCd
	 * @return List<AccountsNameList>
	 */
	List<AccountsNameList> getNameList(final String accountsCd);
}
