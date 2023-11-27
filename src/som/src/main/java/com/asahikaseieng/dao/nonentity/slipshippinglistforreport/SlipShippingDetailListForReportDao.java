/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshippinglistforreport;

import java.util.List;

/**
 * SlipShippingDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface SlipShippingDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingDetailListForReport.class;

	/** ARGSアノテーション getDetailSearchList */
	String getDetailSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<SlipShippingDetailListForReport>
	 */
	List<SlipShippingDetailListForReport> getDetailSearchList(
			SlipShippingListConditionForReport condition);
}
