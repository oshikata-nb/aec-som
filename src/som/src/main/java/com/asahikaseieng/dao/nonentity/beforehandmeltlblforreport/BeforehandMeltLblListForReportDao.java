/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport;

import java.util.List;

/**
 * BeforehandMeltLblListForReportDaoクラス
 * @author kanri-user
 */
public interface BeforehandMeltLblListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<BeforehandMeltLblListForReport>
	 */
	List<BeforehandMeltLblListForReport> getReportList(
			BeforehandMeltLblListConditionForReport condition);
}
