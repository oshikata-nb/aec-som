/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.billissue;

import java.util.List;


/**
 * 
 * BalanceListDao．請求書発行
 * @author tosco
 */
public interface BillIssueListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.billissue.BillIssueList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return BillIssueList 請求書発行結果
	 */
	List<BillIssueList> getSearchList(BillIssuePagerCondition condition);


}
