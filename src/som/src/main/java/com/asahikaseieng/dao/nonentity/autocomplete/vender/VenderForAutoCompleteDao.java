/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender;

import java.util.List;

/**
 * VenderListForAutoCompleteDaoクラス
 * @author tosco
 */
public interface VenderForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.vender.VenderForAutoComplete.class;

	// 取引先区分、取引先コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "venderCd, venderDivision,rowlimit";

	/**
	 * getSearchListメソッド
	 * 
	 * @param venderCd venderCd
	 * @param venderDivision venderDivision
	 * @param rowlimit 行上限
	 * @return VenderForAutoComplete
	 */
	List<VenderForAutoComplete> getSearchList(final String venderCd,
			final String venderDivision, final String rowlimit);
}
