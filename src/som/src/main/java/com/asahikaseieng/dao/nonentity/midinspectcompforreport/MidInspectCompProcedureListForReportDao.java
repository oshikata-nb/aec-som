/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.midinspectcompforreport;

import java.util.List;

/**
 * MidInspectCompProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface MidInspectCompProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<MidInspectCompProcedureListForReport>
	 */
	List<MidInspectCompProcedureListForReport> getProcedureReportList(
			final MidInspectCompListConditionForReport condition);
}
