/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.bumon;

import java.util.List;

/**
 * 会計部門マスタオートコンプリートDaoクラス
 * @author tosco
 */
public interface BumonForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.bumon.BumonForAutoComplete.class;

	// 会計部門コード・会計部門名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "sectionCd,rowlimit";

	/**
	 * getSearchListメソッド
	 * 
	 * @param sectionCd 会計部門コードまたは会計部門名称
	 * @param rowlimit rowlimit
	 * @return BumonForAutoComplete 検索結果リスト
	 */
	List<BumonForAutoComplete> getSearchList(final String sectionCd,
			final String rowlimit);
}
