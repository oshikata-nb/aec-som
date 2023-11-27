/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.operation;

import java.math.BigDecimal;
import java.util.List;

/**
 * OperationListForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface OperationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.operation.OperationForAutoComplete.class;

	// 工程コード・名称、用途で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "operationCd,recipeUse,rowlimit";

	/**
	 * getSearchListメソッド
	 * 
	 * @param operationCd operationCd
	 * @param recipeUse recipeUse
	 * @param rowlimit 行上限
	 * @return OperationForAutoComplete
	 */
	List<OperationForAutoComplete> getSearchList(final String operationCd,
			final BigDecimal recipeUse, final String rowlimit);
}
