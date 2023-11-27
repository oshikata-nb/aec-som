/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.bank;

import java.util.List;

/**
 * BankForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface BankForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.bank.BankForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "bankMasterCd,rowlimit";

	/**
	 * Listメソッド
	 * 
	 * @param bankMasterCd bankMasterCd
	 * @param rowlimit rowlimit
	 * @return List<BankForAutoComplete>
	 */
	List<BankForAutoComplete> getSearchList(final String bankMasterCd,
			final String rowlimit);

	/** ARGSアノテーション getBankSearchList */
	String getBankSearchList_ARGS = "bankCd,rowlimit";

	/**
	 * Listメソッド
	 * 
	 * @param bankCd bankCd
	 * @param rowlimit rowlimit
	 * @return List<BankForAutoComplete>
	 */
	List<BankForAutoComplete> getBankSearchList(final String bankCd,
			final String rowlimit);

	/** ARGSアノテーション getBranchSearchList */
	String getBranchSearchList_ARGS = "bankCd,branchCd,rowlimit";

	/**
	 * Listメソッド
	 * 
	 * @param bankCd bankCd
	 * @param branchCd branchCd
	 * @param rowlimit rowlimit
	 * @return List<BankForAutoComplete>
	 */
	List<BankForAutoComplete> getBranchSearchList(final String bankCd,
			final String branchCd, final String rowlimit);
}
