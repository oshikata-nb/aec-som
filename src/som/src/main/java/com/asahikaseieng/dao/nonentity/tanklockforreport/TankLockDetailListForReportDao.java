/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.tanklockforreport;

import java.util.List;

/**
 * TankLockDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface TankLockDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<TankLockDetailListForReport>
	 */
	List<TankLockDetailListForReport> getDetailReportList(
			final TankLockListConditionForReport condition);
}
