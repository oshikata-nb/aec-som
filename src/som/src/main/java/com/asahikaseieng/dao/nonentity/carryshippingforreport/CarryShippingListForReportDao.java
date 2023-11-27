/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.carryshippingforreport;

import java.util.List;

/**
 * CarryShippingListForReportDaoクラス
 * @author kanri-user
 */
public interface CarryShippingListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListForReport.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<CarryShippingListForReport> getSearchList(
			final CarryShippingListConditionForReport condition);
}
