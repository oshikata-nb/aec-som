/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.stockbookingforreport;

import java.util.List;

/**
 * StockBookingProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface StockBookingProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<StockBookingProcedureListForReport>
	 */
	List<StockBookingProcedureListForReport> getProcedureReportList(
			StockBookingListConditionForReport condition);
}
