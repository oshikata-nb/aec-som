/*
 * Created on 2009/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender.deposit;

import java.util.List;

/**
 * DepositVenderForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface DepositVenderForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.vender.deposit.DepositVenderForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "venderDivision, venderCd, rowlimit";

	/**
	 * Listメソッド
	 * 
	 * @param venderDivision venderDivision
	 * @param venderCd venderCd
	 * @param rowlimit rowlimit
	 * @return List<DepositVenderForAutoComplete>
	 */
	List<DepositVenderForAutoComplete> getSearchList(
			final String venderDivision, final String venderCd,
			final String rowlimit);
}
