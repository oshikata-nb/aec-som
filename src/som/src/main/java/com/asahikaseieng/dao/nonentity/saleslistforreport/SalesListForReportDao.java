/*
 * Created on 2009/07/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.saleslistforreport;

import java.util.List;

/**
 * SalesListForReportDaoクラス
 * @author kanri-user
 */
public interface SalesListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * SalesListForReportメソッド
	 * @param condition 検索条件
	 * @return List<SalesListForReport>
	 */
	List<SalesListForReport> getListForReport(SalesListReportCondition condition);
}
