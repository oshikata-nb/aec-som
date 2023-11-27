/*
 * Created on 2020/11/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportlistforreport;

import java.util.List;

/**
 * OrderListForReportDaoクラス
 * @author kanri-user
 */
public interface OrderImportListForReportDao {

	/** BEANアノテーション */
	Class<OrderImportListForReport> BEAN = com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * OrderListForReportメソッド
	 * @param condition condition
	 * @return List<OrderImportListForReport>
	 */
	List<OrderImportListForReport> getHeaderListForReport(OrderImportListConditionForReport condition);
	
	/**
	 * OrderListForReportメソッド
	 * @param condition condition
	 * @return List<OrderImportListForReport>
	 */
	List<OrderImportListForReport> getDetailListForReport(OrderImportListConditionForReport condition);
}
