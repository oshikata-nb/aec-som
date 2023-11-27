/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalance;

import java.util.List;


/**
 * 
 * ArBalanceListDao．売掛残高一覧
 * @author tosco
 */
public interface ArBalanceListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return ArBalanceList 売掛残高一覧
	 */
	List<ArBalanceList> getSearchList(ArBalancePagerCondition condition);

	/** ARGSアノテーション getSearchDate(). */
	String getSearchDate_ARGS = "sectionCd";

	/**
	 * MAX(締め日)の年月取得メソッド.
	 * @param sectionCd  部門コード
	 * @return ArBalanceList ヘッダーデータ(MAX(売掛締め日の年月)
	 */
	ArBalanceList getSearchDate(String sectionCd);

}
