/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchaseforreport;

import java.util.List;

/**
 * PurchaseListForReportDaoクラス
 * @author kanri-user
 */
public interface PurchaseListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<PurchaseListForReport> getReportList(
			final PurchaseListConditionForReport condition);
}
