/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.area;

import java.util.List;

/**
 * AreaForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface AreaForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.area.AreaForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "areaCd,rowlimit";

	/**
	 * AreaForAutoCompleteメソッド
	 * 
	 * @param areaCd areaCd
	 * @param rowlimit rowlimit
	 * @return List<AreaForAutoComplete>
	 */
	List<AreaForAutoComplete> getSearchList(final String areaCd,
			final String rowlimit);
}
