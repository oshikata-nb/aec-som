/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location.sales;

import java.util.List;

/**
 * SalesLocationForAutoCompleteDaoクラス
 * @author tosco
 */
public interface SalesLocationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.location.sales.SalesLocationForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "locationCd,itemCd,rowlimit";

	// ロケーションコード・名称、品目コードで検索-----------------------------------------------------
	/**
	 * SalesLocationForAutoCompleteメソッド
	 * 
	 * @param locationCd ロケーションコード
	 * @param itemCd 品目コード
	 * @param rowlimit 行上限
	 * @return ShippingLocationListForAutoComplete
	 */
	List<SalesLocationForAutoComplete> getSearchList(final String locationCd,
			final String itemCd, final String rowlimit);

}
