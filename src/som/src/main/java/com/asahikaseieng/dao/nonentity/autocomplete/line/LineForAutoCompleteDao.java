/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.line;

import java.util.List;

/**
 * LineForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface LineForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.line.LineForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "productionLine,rowlimit";

	/**
	 * getSearchListメソッド
	 * 
	 * @param productionLine productionLine
	 * @param rowlimit 行上限
	 * @return LineForAutoComplete
	 */
	List<LineForAutoComplete> getSearchList(final String productionLine,
			final String rowlimit);
}
