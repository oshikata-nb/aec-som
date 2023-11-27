/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledger;

import java.util.List;


/**
 * 
 * ApLedgerListDao．買掛元帳
 * @author tosco
 */
public interface ApLedgerListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return List<ApLedgerList> 買掛元帳一覧
	 */
	List<ApLedgerList> getSearchList(ApLedgerPagerCondition condition);


}
