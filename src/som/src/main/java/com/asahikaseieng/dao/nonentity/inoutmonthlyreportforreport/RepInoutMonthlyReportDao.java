/*
 * Created on 2009/09/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport;

import java.util.List;

/**
 * RepInoutMonthlyReportDaoクラス
 * @author kanri-user
 */
public interface RepInoutMonthlyReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport.RepInoutMonthlyReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "dateFrom, dateTo";

	/**
	 * RepInoutMonthlyReportメソッド
	 * 
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @return RepInoutMonthlyReport
	 */
	List<RepInoutMonthlyReport> getListForReport(final String dateFrom,
			final String dateTo);
}
