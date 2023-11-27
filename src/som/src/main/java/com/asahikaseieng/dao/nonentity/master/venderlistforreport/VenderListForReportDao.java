/*
 * Created on 2009/05/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderlistforreport;

import java.util.List;

/**
 * VenderListForReportDaoクラス
 * @author t0011036
 */
public interface VenderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * VenderListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<VenderListForReport>
	 */
	List<VenderListForReport> getListForReport(
			final VenderListConditionForReport condition);
}
