/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslistforreport;

import java.util.List;

/**
 * SalesTermsListForReportDaoクラス
 * @author t0011036
 */
public interface SalesTermsListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * SalesTermsListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<SalesTermsListForReport>
	 */
	List<SalesTermsListForReport> getListForReport(
			final SalesTermsListConditionForReport condition);
}
