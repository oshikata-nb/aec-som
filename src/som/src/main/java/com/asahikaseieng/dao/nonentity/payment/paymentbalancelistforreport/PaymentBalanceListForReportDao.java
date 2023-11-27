/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport;

import java.util.List;

/**
 * PaymentBalanceListForReportDaoクラス
 * @author t0011036
 */
public interface PaymentBalanceListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * PaymentBalanceListForReportメソッド
	 * @param condition 検索条件
	 * @return List<PaymentBalanceListForReport>
	 */
	List<PaymentBalanceListForReport> getListForReport(
			final PaymentBalanceListConditionForReport condition);
}
