/*
 * Created on 2009/07/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup;

import java.util.List;

/**
 * OffsetGroupForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface OffsetGroupForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup.OffsetGroupForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "offsetGroupCd, rowlimit";

	/**
	 * Listメソッド
	 * 
	 * @param offsetGroupCd offsetGroupCd
	 * @param rowlimit rowlimit
	 * @return List<OffsetGroupForAutoComplete>
	 */
	List<OffsetGroupForAutoComplete> getSearchList(final String offsetGroupCd,
			final String rowlimit);
}
