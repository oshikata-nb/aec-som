/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport;

import java.util.List;

/**
 * BeforehandMeltLblFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface BeforehandMeltLblFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<BeforehandMeltLblFormulaListForReport> getFormulaReportList(
			BeforehandMeltLblListConditionForReport condition);
}
