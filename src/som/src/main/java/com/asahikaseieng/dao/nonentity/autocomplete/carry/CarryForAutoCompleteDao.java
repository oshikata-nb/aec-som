/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.carry;

import java.util.List;

/**
 * CarryForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface CarryForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.carry.CarryForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "carryCd,rowlimit";

	/**
	 * CarryForAutoCompleteメソッド
	 * 
	 * @param carryCd carryCd
	 * @param rowlimit rowlimit
	 * @return List<CarryForAutoComplete>
	 */
	List<CarryForAutoComplete> getSearchList(final String carryCd,
			final String rowlimit);
}
