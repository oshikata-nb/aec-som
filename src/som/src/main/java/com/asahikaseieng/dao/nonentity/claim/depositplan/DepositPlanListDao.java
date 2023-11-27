/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositplan;

import java.util.List;


/**
 * 
 * DepositPlanListDao．入金予定一覧
 * @author tosco
 */
public interface DepositPlanListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return BalanceList 入金予定一覧結果
	 */
	List<DepositPlanList> getSearchList(DepositPlanListPagerCondition condition);


}
