/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingforreport;

import java.util.List;

/**
 * ShippingListForReportDaoクラス
 * @author kanri-user
 */
public interface ShippingListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListForReport.class;

	/** ARGSアノテーション getHeaderListForReport */
	String getHeaderListForReport_ARGS = "condition";

	/**
	 * ShippingListForReportメソッド
	 * @param condition condition
	 * @return ShippingListForReport
	 */
	List<ShippingListForReport> getHeaderListForReport(
			final ShippingListConditionForReport condition);
}
