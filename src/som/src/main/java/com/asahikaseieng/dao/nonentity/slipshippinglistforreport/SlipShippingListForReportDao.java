/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshippinglistforreport;

import java.util.List;

/**
 * SlipShippingListForReportDaoクラス
 * @author kanri-user
 */
public interface SlipShippingListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListForReport.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<SlipShippingListForReport>
	 */
	List<SlipShippingListForReport> getSearchList(
			SlipShippingListConditionForReport condition);
}
