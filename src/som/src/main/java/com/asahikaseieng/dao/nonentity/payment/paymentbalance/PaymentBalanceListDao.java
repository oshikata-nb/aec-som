/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentbalance;

import java.util.List;


/**
 * 
 * PaymentBalanceListDao．支払残高一覧
 * @author tosco
 */
public interface PaymentBalanceListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalanceList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return PaymentBalanceList 支払残高一覧
	 */
	List<PaymentBalanceList> getSearchList(PaymentBalancePagerCondition condition);


}
