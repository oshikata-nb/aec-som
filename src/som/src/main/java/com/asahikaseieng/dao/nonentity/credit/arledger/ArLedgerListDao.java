/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledger;

import java.util.List;


/**
 * 
 * ArLedgerListDao．売掛元帳
 * @author tosco
 */
public interface ArLedgerListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return List<ArLedgerList 売掛元帳一覧
	 */
	List<ArLedgerList> getSearchList(ArLedgerPagerCondition condition);


}
