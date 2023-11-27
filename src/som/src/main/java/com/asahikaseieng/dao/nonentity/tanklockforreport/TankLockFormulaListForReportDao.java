/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.tanklockforreport;

import java.util.List;

/**
 * TankLockFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface TankLockFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<TankLockFormulaListForReport>
	 */
	List<TankLockFormulaListForReport> getFormulaReportList(
			final TankLockListConditionForReport condition);
}
