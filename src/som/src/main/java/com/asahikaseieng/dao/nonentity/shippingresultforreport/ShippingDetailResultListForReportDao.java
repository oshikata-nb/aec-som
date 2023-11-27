/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingresultforreport;

import java.util.List;

/**
 * ShippingDetailResultListForReportDaoクラス
 * @author kanri-user
 */
public interface ShippingDetailResultListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingDetailResultListForReport.class;

	/** ARGSアノテーション getDetailSearchList */
	String getDetailSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<ShippingDetailResultListForReport>
	 */
	List<ShippingDetailResultListForReport> getDetailSearchList(
			final ShippingResultListConditionForReport condition);
}
