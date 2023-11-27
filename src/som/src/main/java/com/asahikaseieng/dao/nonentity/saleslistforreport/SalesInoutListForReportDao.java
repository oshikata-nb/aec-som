/*
 * Created on 2009/07/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.saleslistforreport;

import java.util.List;

/**
 * SalesInoutListForReportDaoクラス
 * @author kanri-user
 */
public interface SalesInoutListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.saleslistforreport.SalesInoutListForReport.class;

	/** ARGSアノテーション getInoutListForReport */
	String getInoutListForReport_ARGS = "condition";

	/**
	 * SalesInoutListForReportメソッド
	 * @param condition 検索条件
	 * @return List<SalesListForReport>
	 */
	List<SalesInoutListForReport> getInoutListForReport(
			final SalesListReportCondition condition);
}
