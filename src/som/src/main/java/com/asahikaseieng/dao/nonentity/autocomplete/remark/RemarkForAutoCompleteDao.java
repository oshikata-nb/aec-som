/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.remark;

import java.util.List;

/**
 * RemarkForAutoCompleteDaoクラス
 * @author kanri-user
 */
public interface RemarkForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.remark.RemarkForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "venderDivision, venderCd, deliveryCd, itemCd,rowlimit";

	/**
	 * RemarkForAutoCompleteメソッド
	 * 
	 * @param venderDivision venderDivision
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @param rowlimit 行上限
	 * @return List<RemarkForAutoComplete>
	 */
	List<RemarkForAutoComplete> getSearchList(final String venderDivision,
			final String venderCd, final String deliveryCd,
			final String itemCd, final String rowlimit);
}
