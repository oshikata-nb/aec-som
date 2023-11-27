/*
 * Created on 2009/08/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyingforreport;

import java.util.List;

/**
 * BuyingListForReportDaoクラス
 * @author kanri-user
 */
public interface BuyingListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.buyingforreport.BuyingListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<BuyingListForReport> getReportList(
			final BuyingListConditionForReport condition);
}
