/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.operationgroup;

import java.util.List;

/**
 * OperationGroupForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface OperationGroupForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.operationgroup.OperationGroupForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "operationGroupCd,rowlimit";

	/**
	 * OperationGroupForAutoCompleteメソッド
	 * 
	 * @param operationGroupCd operationGroupCd
	 * @param rowlimit 行上限
	 * @return List<OperationGroupForAutoComplete>
	 */
	List<OperationGroupForAutoComplete> getSearchList(
			final String operationGroupCd, final String rowlimit);
}
