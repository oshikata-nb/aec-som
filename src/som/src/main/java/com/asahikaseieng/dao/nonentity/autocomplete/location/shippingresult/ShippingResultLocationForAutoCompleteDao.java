/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location.shippingresult;

import java.util.List;

/**
 * ShippingResultLocationForAutoCompleteDaoクラス
 * @author tosco
 */
public interface ShippingResultLocationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.location.shippingresult.ShippingResultLocationForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "locationCd,itemCd,rowlimit";

	// ロケーションコード・名称、品目コードで検索-----------------------------------------------------
	/**
	 * ShippingResultLocationForAutoCompleteメソッド
	 * 
	 * @param locationCd ロケーションコード
	 * @param itemCd 品目コード
	 * @param rowlimit 行上限
	 * @return ShippingResultLocationListForAutoComplete
	 */
	List<ShippingResultLocationForAutoComplete> getSearchList(
			final String locationCd, final String itemCd, final String rowlimit);

}
