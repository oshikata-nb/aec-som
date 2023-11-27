/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.stockbookingforreport;

import java.util.List;

/**
 * StockBookingFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface StockBookingFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<StockBookingFormulaListForReport>
	 */
	List<StockBookingFormulaListForReport> getFormulaReportList(
			StockBookingListConditionForReport condition);
}
