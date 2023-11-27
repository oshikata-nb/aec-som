/*
 * Created on 2009/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companylistforreport;

import java.util.List;

/**
 * CompanyListForReportDaoクラス
 * @author t0011036
 */
public interface CompanyListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * CompanyListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<CompanyListForReport>
	 */
	List<CompanyListForReport> getListForReport(
			final CompanyListConditionForReport condition);
}
