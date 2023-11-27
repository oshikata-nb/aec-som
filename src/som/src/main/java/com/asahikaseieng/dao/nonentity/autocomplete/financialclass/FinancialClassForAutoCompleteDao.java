/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.financialclass;

import java.util.List;

/**
 * FinancialClassForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface FinancialClassForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.financialclass.FinancialClassForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "financialClassCd,rowlimit";

	/**
	 * Listメソッド
	 * 
	 * @param financialClassCd financialClassCd
	 * @param rowlimit rowlimit
	 * @return List<FinancialClassForAutoComplete>
	 */
	List<FinancialClassForAutoComplete> getSearchList(
			final String financialClassCd, final String rowlimit);
}
