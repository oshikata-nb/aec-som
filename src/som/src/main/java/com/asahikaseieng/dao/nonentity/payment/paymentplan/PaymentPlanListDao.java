/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentplan;

import java.util.List;


/**
 * 
 * PaymentPlanListDao．支払予定一覧
 * @author tosco
 */
public interface PaymentPlanListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return List<PaymentPlanList> 支払予定一覧結果
	 */
	List<PaymentPlanList> getSearchList(PaymentPlanPagerCondition condition);


}
