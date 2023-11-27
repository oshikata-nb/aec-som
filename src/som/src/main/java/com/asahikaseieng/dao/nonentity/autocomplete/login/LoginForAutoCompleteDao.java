/*
 * Created on 2009/01/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.login;

import java.util.List;

/**
 * LoginForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface LoginForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "tantoCd,rowlimit";

	/**
	 * LoginForAutoCompleteメソッド
	 * @param tantoCd tantoCd
	 * @param rowlimit 行上限
	 * @return List<LoginForAutoComplete>
	 */
	List<LoginForAutoComplete> getSearchList(final String tantoCd,
			final String rowlimit);
}
