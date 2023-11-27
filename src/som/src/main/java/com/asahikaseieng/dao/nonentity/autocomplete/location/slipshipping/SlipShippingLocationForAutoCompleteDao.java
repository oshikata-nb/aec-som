/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location.slipshipping;

import java.util.List;

/**
 * SlipShippingLocationForAutoCompleteDaoクラス
 * @author tosco
 */
public interface SlipShippingLocationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.location.slipshipping.SlipShippingLocationForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "locationCd,rowlimit";

	/**
	 * 上位ロケーション一覧取得用
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @param rowlimit 行上限
	 * @return List<SlipShippingLocationForAutoComplete>
	 */
	List<SlipShippingLocationForAutoComplete> getSearchList(
			final String locationCd, final String rowlimit);

}
