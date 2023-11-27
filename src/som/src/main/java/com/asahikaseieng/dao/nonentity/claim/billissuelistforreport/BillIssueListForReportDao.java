/*
 * Created on 2009/08/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.billissuelistforreport;

import java.util.List;

/**
 * BillIssueListForReportDaoクラス
 * @author t0011036
 */
public interface BillIssueListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * BillIssueListForReportメソッド
	 * @param condition 検索条件
	 * @return List<BillIssueListForReport>
	 */
	List<BillIssueListForReport> getListForReport(
			final BillIssueListConditionForReport condition);
}
