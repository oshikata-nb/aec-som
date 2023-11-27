/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlistforreport;

import java.util.List;

/**
 * LoginListForReportDaoクラス
 * @author t0011036
 */
public interface LoginListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * LoginListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<LoginListForReport>
	 */
	List<LoginListForReport> getListForReport(
			final LoginListConditionForReport condition);
}
