/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apbalance;

import java.util.List;


/**
 * 
 * ArLedgerListDao．買掛残高一覧
 * @author tosco
 */
public interface ApBalanceListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.apbalance.ApBalanceList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * 買掛ヘッダー検索.
	 * @param condition 検索条件
	 * @return ArBalanceList 買掛残高一覧(本締め)
	 */
	List<ApBalanceList> getSearchList(ApBalancePagerCondition condition);

	/** ARGSアノテーション getSearchListTemp(). */
	String getSearchListTemp_ARGS = "condition";

	/**
	 * 仮締め買掛ヘッダー検索.
	 * @param condition 検索条件
	 * @return ArBalanceList 買掛残高一覧(仮締め)
	 */
	List<ApBalanceList> getSearchListTemp(ApBalancePagerCondition condition);


}
