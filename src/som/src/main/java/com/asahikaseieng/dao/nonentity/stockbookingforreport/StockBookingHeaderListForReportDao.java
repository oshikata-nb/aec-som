/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.stockbookingforreport;

import java.util.List;

/**
 * StockBookingHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface StockBookingHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingHeaderListForReport.class;

	/** ARGSアノテーション getHeaderReportList */
	String getHeaderReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<StockBookingHeaderListForReport>
	 */
	List<StockBookingHeaderListForReport> getHeaderReportList(
			StockBookingListConditionForReport condition);
}
