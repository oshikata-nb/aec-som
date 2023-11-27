/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.util.List;

/**
 * PaymentListForReportDaoクラス
 * @author t0011036
 */
public interface PaymentListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * PaymentListForReportメソッド
	 * @param condition 検索条件
	 * @return List<PaymentListForReport>
	 */
	List<PaymentListForReport> getListForReport(
			final PaymentListConditionForReport condition);
}
