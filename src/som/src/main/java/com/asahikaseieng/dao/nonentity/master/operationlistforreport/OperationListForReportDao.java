/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationlistforreport;

import java.util.List;

/**
 * OperationListForReportDaoクラス
 * @author t0011036
 */
public interface OperationListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * OperationListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return OperationListForReport
	 */
	List<OperationListForReport> getListForReport(
			final OperationListConditionForReport condition);
}
