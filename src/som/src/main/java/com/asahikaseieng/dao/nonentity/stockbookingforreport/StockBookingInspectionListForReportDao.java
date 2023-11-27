/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.stockbookingforreport;

import java.util.List;

/**
 * StockBookingInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface StockBookingInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<StockBookingInspectionListForReport>
	 */
	List<StockBookingInspectionListForReport> getInspectionReportList(
			StockBookingListConditionForReport condition);
}
