/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchasedeliveryforreport;

import java.util.List;

/**
 * PurchaseDeliveryListForReportDaoクラス
 * @author kanri-user
 */
public interface PurchaseDeliveryListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PurchaseDeliveryListForReport>
	 */
	List<PurchaseDeliveryListForReport> getReportList(
			PurchaseDeliveryListConditionForReport condition);
}
