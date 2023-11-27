/*
 * Created on 2009/06/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location.order;

import java.util.List;

/**
 * OrderLocationListForAutoCompleteDaoクラス
 * @author kanri-user
 */
public interface OrderLocationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.location.order.OrderLocationForAutoComplete.class;

	/** ARGSアノテーション getListForAutoComplete */
	String getSearchList_ARGS = "locationCd,itemCd,rowlimit";

	/**
	 * OrderLocationListForAutoCompleteメソッド
	 * 
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param rowlimit 行上限
	 * @return OrderLocationListForAutoComplete
	 */
	List<OrderLocationForAutoComplete> getSearchList(final String locationCd,
			final String itemCd, final String rowlimit);
}
