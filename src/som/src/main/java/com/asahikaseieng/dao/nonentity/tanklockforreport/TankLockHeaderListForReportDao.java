/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.tanklockforreport;

import java.util.List;

/**
 * TankLockListForReportDaoクラス
 * @author kanri-user
 */
public interface TankLockHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockHeaderListForReport.class;

	/** ARGSアノテーション getHeaderReportList */
	String getHeaderReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<TankLockListForReport>
	 */
	List<TankLockHeaderListForReport> getHeaderReportList(
			final TankLockListConditionForReport condition);
}
