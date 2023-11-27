/*
 * Created on 2009/08/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionstatuschangeforreport;

import java.util.List;

/**
 * DirectionStatusChangeFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionStatusChangeFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<DirectionStatusChangeFormulaListForReport> getFormulaReportList(
			DirectionStatusChangeListConditionForReport condition);
}
