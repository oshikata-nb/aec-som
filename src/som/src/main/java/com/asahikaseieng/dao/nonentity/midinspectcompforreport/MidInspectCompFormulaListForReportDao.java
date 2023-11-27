/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.midinspectcompforreport;

import java.util.List;

/**
 * MidInspectCompFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface MidInspectCompFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<MidInspectCompFormulaListForReport>
	 */
	List<MidInspectCompFormulaListForReport> getFormulaReportList(
			final MidInspectCompListConditionForReport condition);
}
