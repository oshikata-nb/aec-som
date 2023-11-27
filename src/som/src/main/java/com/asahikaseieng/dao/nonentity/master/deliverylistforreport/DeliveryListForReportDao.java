/*
 * Created on 2009/05/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverylistforreport;

import java.util.List;

/**
 * DeliveryListForReportDaoクラス
 * @author t0011036
 */
public interface DeliveryListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * DeliveryListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<DeliveryListForReport>
	 */
	List<DeliveryListForReport> getListForReport(
			final DeliveryListConditionForReport condition);
}
