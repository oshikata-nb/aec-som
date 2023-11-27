/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.tanklockforreport;

import java.util.List;

/**
 * TankLockInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface TankLockInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<TankLockInspectionListForReport>
	 */
	List<TankLockInspectionListForReport> getInspectionReportList(
			final TankLockListConditionForReport condition);
}
