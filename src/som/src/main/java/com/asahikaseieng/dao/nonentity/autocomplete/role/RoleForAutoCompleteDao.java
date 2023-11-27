/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.role;

import java.util.List;

/**
 * RoleForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface RoleForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.role.RoleForAutoComplete.class;

	/** ARGSアノテーション getForAutoComplete */
	String getSearchList_ARGS = "roleId,rowlimit";

	/**
	 * RoleForAutoCompleteメソッド
	 * 
	 * @param roleId roleId
	 * @param rowlimit 行上限
	 * @return RoleForAutoComplete
	 */
	List<RoleForAutoComplete> getSearchList(final String roleId,
			final String rowlimit);
}
