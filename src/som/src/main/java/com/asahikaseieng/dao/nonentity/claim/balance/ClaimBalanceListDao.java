/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.balance;

import java.util.List;


/**
 * 
 * BalanceListDao．請求残高一覧
 * @author tosco
 */
public interface ClaimBalanceListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalanceList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return BalanceList 請求残高一覧結果
	 */
	List<ClaimBalanceList> getSearchList(ClaimBalancePagerCondition condition);


}
