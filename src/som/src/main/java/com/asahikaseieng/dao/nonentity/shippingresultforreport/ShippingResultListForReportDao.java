/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingresultforreport;

import java.util.List;

/**
 * ShippingResultListForReportDaoクラス
 * @author kanri-user
 */
public interface ShippingResultListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListForReport.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<ShippingResultListForReport>
	 */
	List<ShippingResultListForReport> getSearchList(
			ShippingResultListConditionForReport condition);
}
