/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderlistforreport;

import java.util.List;

/**
 * OrderListForReportDaoクラス
 * @author kanri-user
 */
public interface OrderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * OrderListForReportメソッド
	 * @param condition condition
	 * @return OrderListForReport
	 */
	List<OrderListForReport> getListForReport(OrderListConditionForReport condition);
}
