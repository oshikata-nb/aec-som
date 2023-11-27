/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingforreport;

import java.util.List;

/**
 * ShippingDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface ShippingDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingforreport.ShippingDetailListForReport.class;

	/** ARGSアノテーション getDetailListForReport */
	String getDetailListForReport_ARGS = "condition";

	/**
	 * ShippingDetailListForReportメソッド
	 * @param condition condition
	 * @return ShippingListForReport
	 */
	List<ShippingDetailListForReport> getDetailListForReport(
			final ShippingListConditionForReport condition);
}
