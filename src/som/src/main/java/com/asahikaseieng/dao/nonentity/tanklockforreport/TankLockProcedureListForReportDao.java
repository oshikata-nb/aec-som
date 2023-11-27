/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.tanklockforreport;

import java.util.List;

/**
 * TankLockProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface TankLockProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<TankLockProcedureListForReport>
	 */
	List<TankLockProcedureListForReport> getProcedureReportList(final TankLockListConditionForReport condition);
}
