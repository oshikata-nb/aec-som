/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belonglistforreport;

import java.util.List;

/**
 * BelongListForReportDaoクラス
 * @author t0011036
 */
public interface BelongListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * BelongListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<BelongListForReport>
	 */
	List<BelongListForReport> getListForReport(
			final BelongListConditionForReport condition);
}
