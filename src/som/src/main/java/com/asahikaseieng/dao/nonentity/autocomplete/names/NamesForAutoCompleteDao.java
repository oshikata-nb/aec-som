/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.names;

import java.util.List;

/**
 * 各種名称マスタオートコンプリートDaoクラス
 * @author tosco
 */
public interface NamesForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.names.NamesForAutoComplete.class;

	// 名称区分、名称コード・名称１で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "nameCd, nameDivision,rowlimit";

	/**
	 * getSearchListメソッド
	 * 
	 * @param nameCd 名称コード
	 * @param nameDivision 名称区分
	 * @param rowlimit 行上限
	 * @return NamesForAutoComplete 各種名称マスタオートコンプリートBean
	 */
	List<NamesForAutoComplete> getSearchList(final String nameCd,
			final String nameDivision, final String rowlimit);
}
