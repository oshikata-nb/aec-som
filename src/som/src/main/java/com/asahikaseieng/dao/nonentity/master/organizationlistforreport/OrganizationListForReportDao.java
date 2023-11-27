/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlistforreport;

import java.util.List;

/**
 * OrganizationListForReportDaoクラス
 * @author t0011036
 */
public interface OrganizationListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * OrganizationListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<OrganizationListForReport>
	 */
	List<OrganizationListForReport> getListForReport(
			final OrganizationListConditionForReport condition);
}
