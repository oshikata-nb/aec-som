/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.midinspectcompforreport;

import java.util.List;

/**
 * MidInspectCompHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface MidInspectCompHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompHeaderListForReport.class;

	/** ARGSアノテーション getHeaderReportList */
	String getHeaderReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<MidInspectCompHeaderListForReport>
	 */
	List<MidInspectCompHeaderListForReport> getHeaderReportList(
			final MidInspectCompListConditionForReport condition);
}
