/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location.buying;

import java.util.List;

/**
 * BuyingLocationForAutoCompleteDaoクラス
 * @author tosco
 */
public interface BuyingLocationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.location.buying.BuyingLocationForAutoComplete.class;

	// 納入先コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "locationCd,rowlimit";

	/**
	 * 検索画面用納入先一覧取得用
	 * @param locationCd 納入先コードまたは納入先名
	 * @param rowlimit 行上限
	 * @return List<BuyingLocationForAutoComplete>
	 */
	List<BuyingLocationForAutoComplete> getSearchList(final String locationCd,
			final String rowlimit);

}
