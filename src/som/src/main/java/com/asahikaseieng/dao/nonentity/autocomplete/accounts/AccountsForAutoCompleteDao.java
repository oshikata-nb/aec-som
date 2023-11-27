/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.accounts;

import java.util.List;

/**
 * AccountsForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface AccountsForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.accounts.AccountsForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "accountsCd,rowlimit";

	/**
	 * AccountsForAutoCompleteメソッド
	 * 
	 * @param accountsCd accountsCd
	 * @param rowlimit rowlimit
	 * @return List<AccountsForAutoComplete>
	 */
	List<AccountsForAutoComplete> getSearchList(final String accountsCd,
			final String rowlimit);
}
