/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location.shipping;

import java.util.List;

/**
 * ShippingLocationForAutoCompleteDaoクラス
 * @author tosco
 */
public interface ShippingLocationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.location.shipping.ShippingLocationForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "locationCd,itemCd,rowlimit";

	// ロケーションコード・名称、品目コードで検索-----------------------------------------------------
	/**
	 * ShippingLocationForAutoCompleteメソッド
	 * 
	 * @param locationCd ロケーションコード
	 * @param itemCd 品目コード
	 * @param rowlimit 行上限
	 * @return ShippingLocationListForAutoComplete
	 */
	List<ShippingLocationForAutoComplete> getSearchList(
			final String locationCd, final String itemCd, final String rowlimit);

}
