/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location.slipsales;

import java.util.List;

/**
 * SlipSalesLocationForAutoCompleteDaoクラス
 * @author tosco
 */
public interface SlipSalesLocationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.location.slipsales.SlipSalesLocationForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "locationCd,rowlimit";

	/**
	 * 上位ロケーション一覧取得用
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @param rowlimit 行上限
	 * @return List<SlipSalesLocationForAutoComplete>
	 */
	List<SlipSalesLocationForAutoComplete> getSearchList(
			final String locationCd, final String rowlimit);

}
