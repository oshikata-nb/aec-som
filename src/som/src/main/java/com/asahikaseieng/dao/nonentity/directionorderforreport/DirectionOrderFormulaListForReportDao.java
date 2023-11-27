/*
 * Created on 2009/08/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionorderforreport;

import java.util.List;

/**
 * DirectionOrderFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionOrderFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionOrderFormulaListForReport>
	 */
	List<DirectionOrderFormulaListForReport> getFormulaReportList(
			DirectionOrderListConditionForReport condition);
}
