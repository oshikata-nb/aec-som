/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirectionorforreport;

import java.util.List;

/**
 * DirectionResultFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionResultFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionResultFormulaListForReport>
	 */
	List<DirectionResultFormulaListForReport> getFormulaReportList(DirectionResultListConditionForReport condition
	);
}
