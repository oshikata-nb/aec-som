/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reason;

import java.util.List;

/**
 * 理由マスタオートコンプリートDaoクラス
 * @author tosco
 */
public interface ReasonForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.reason.ReasonForAutoComplete.class;

	// 科目コード・科目名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "ryCd,rowlimit";

	/**
	 * getSearchListメソッド
	 * 
	 * @param ryCd 理由コードまたは理由内容
	 * @param rowlimit 行上限
	 * @return ReasonForAutoComplete 検索結果リスト
	 */
	List<ReasonForAutoComplete> getSearchList(final String ryCd,
			final String rowlimit);
}
