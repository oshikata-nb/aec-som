/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location;

import java.util.List;

/**
 * LocationForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface LocationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.location.LocationForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "locationCd,rowlimit";

	/**
	 * LocationForAutoCompleteメソッド
	 * 
	 * @param locationCd locationCd
	 * @param rowlimit 行上限
	 * @return List<LocationForAutoComplete>
	 */
	List<LocationForAutoComplete> getSearchList(final String locationCd,
			final String rowlimit);

	/** ARGSアノテーション getSearchAvailableList */
	String getSearchAvailableList_ARGS = "locationCd,rowlimit";

	/**
	 * LocationForAutoCompleteメソッド
	 * 
	 * @param locationCd locationCd
	 * @param rowlimit 行上限
	 * @return List<LocationForAutoComplete>
	 */
	List<LocationForAutoComplete> getSearchAvailableList(
			final String locationCd, final String rowlimit);
}
