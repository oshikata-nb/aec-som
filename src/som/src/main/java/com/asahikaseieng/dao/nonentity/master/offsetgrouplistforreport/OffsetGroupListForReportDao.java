/*
 * Created on 2009/08/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport;

import java.util.List;

/**
 * OffsetGroupListForReportDaoクラス
 * @author t0011036
 */
public interface OffsetGroupListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * OffsetGroupListForReportメソッド
	 * @param condition 検索条件
	 * @return List<OffsetGroupListForReport>
	 */
	List<OffsetGroupListForReport> getListForReport(
			final OffsetGroupListConditionForReport condition);
}
