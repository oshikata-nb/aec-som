/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.cal;

import java.util.List;

/**
 * CalForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface CalForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.cal.CalForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "calCd,rowlimit";

	/**
	 * CalForAutoCompleteメソッド
	 * 
	 * @param calCd calCd
	 * @param rowlimit rowlimit
	 * @return CalForAutoComplete
	 */
	List<CalForAutoComplete> getSearchList(final String calCd,
			final String rowlimit);
}
