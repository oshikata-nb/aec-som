/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport;

import java.util.List;

/**
 * BeforehandMeltLblInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface BeforehandMeltLblInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<BeforehandMeltLblInspectionListForReport
	 */
	List<BeforehandMeltLblInspectionListForReport> getInspectionReportList(
			final BeforehandMeltLblListConditionForReport condition);
}
