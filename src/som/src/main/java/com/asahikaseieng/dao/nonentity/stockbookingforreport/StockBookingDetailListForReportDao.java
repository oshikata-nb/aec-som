/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.stockbookingforreport;

import java.util.List;

/**
 * StockBookingDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface StockBookingDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<StockBookingDetailListForReport>
	 */
	List<StockBookingDetailListForReport> getDetailReportList(
			StockBookingListConditionForReport condition);
}
