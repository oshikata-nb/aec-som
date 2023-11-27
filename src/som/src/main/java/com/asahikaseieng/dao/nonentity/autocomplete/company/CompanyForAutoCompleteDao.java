/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.company;

import java.util.List;

/**
 * CompanyForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface CompanyForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.company.CompanyForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "homeName1,rowlimit";

	/**
	 * CompanyForAutoCompleteメソッド
	 * 
	 * @param homeName1 homeName1
	 * @param rowlimit rowlimit
	 * @return CompanyForAutoComplete
	 */
	List<CompanyForAutoComplete> getSearchList(final String homeName1,
			final String rowlimit);
}
