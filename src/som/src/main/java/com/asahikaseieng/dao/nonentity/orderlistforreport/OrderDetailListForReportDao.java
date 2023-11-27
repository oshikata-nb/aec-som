/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderlistforreport;

import java.util.List;

/**
 * OrderDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface OrderDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderlistforreport.OrderDetailListForReport.class;

	/** ARGSアノテーション getDetailListForReport */
	String getDetailListForReport_ARGS = "condition";

	/**
	 * OrderDetailListForReportメソッド
	 * @param condition condition
	 * @return OrderDetailListForReport
	 */
	List<OrderDetailListForReport> getDetailListForReport(
			final OrderListConditionForReport condition);
}
