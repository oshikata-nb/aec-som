/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.component;

import java.util.List;

/**
 * ComponentForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface ComponentForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.component.ComponentForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "componentCd,rowlimit";

	/**
	 * ComponentForAutoCompleteメソッド
	 * 
	 * @param componentCd componentCd
	 * @param rowlimit rowlimit
	 * @return List<ComponentForAutoComplete>
	 */
	List<ComponentForAutoComplete> getSearchList(final String componentCd,
			final String rowlimit);
}
