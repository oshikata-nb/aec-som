/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.midinspectcompforreport;

import java.util.List;

/**
 * MidInspectCompInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface MidInspectCompInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<MidInspectCompInspectionListForReport>
	 */
	List<MidInspectCompInspectionListForReport> getInspectionReportList(
			final MidInspectCompListConditionForReport condition);
}
