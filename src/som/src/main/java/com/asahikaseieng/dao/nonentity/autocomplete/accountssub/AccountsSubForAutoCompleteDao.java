/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.accountssub;

import java.util.List;

/**
 * AccountsSubForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface AccountsSubForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.accountssub.AccountsSubForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "accountsSubCd,rowlimit";

	/**
	 * AccountsSubForAutoCompleteメソッド
	 * 
	 * @param accountsSubCd accountsSubCd
	 * @param rowlimit rowlimit
	 * @return AccountsSubForAutoComplete
	 */
	List<AccountsSubForAutoComplete> getSearchList(final String accountsSubCd,
			final String rowlimit);
}
