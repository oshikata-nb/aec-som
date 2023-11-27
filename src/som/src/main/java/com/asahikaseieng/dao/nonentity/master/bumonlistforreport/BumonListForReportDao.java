/*
 * Created on 2009/05/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlistforreport;

import java.util.List;

/**
 * BumonListForReportDaoクラス
 * @author t0011036
 */
public interface BumonListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * BumonListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<BumonListForReport>
	 */
	List<BumonListForReport> getListForReport(
			final BumonListConditionForReport condition);
}
