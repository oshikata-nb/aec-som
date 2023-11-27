/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.midinspectcompforreport;

import java.util.List;

/**
 * MidInspectCompDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface MidInspectCompDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<MidInspectCompDetailListForReport>
	 */
	List<MidInspectCompDetailListForReport> getDetailReportList(
			final MidInspectCompListConditionForReport condition);
}
