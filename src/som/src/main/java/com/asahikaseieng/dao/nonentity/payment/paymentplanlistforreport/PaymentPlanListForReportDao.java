/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport;

import java.util.List;

/**
 * PaymentPlanListForReportDaoクラス
 * @author t0011036
 */
public interface PaymentPlanListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * PaymentPlanListForReportメソッド
	 * @param condition 検索条件
	 * @return List<PaymentPlanListForReport>
	 */
	List<PaymentPlanListForReport> getListForReport(
			final PaymentPlanListConditionForReport condition);
}
