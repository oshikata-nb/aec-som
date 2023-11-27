/*
 * Created on 2010/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport;

import java.util.List;

/**
 * RepTempInoutMonthlyReportDaoクラス
 * @author kanri-user
 */
public interface RepTempInoutMonthlyReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport.RepTempInoutMonthlyReport.class;

	/** ARGSアノテーション delete */
	String delete_ARGS = "inputorCd";

	/**
	 * 削除処理
	 * 
	 * @param inputorCd inputorCd
	 * @return 削除件数
	 */
	int delete(final String inputorCd);

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "inputorCd";

	/**
	 * RepTempInoutMonthlyReportメソッド
	 * 
	 * @param inputorCd inputorCd
	 * @return RepTempInoutMonthlyReport
	 */
	List<RepTempInoutMonthlyReport> getListForReport(final String inputorCd);
}
