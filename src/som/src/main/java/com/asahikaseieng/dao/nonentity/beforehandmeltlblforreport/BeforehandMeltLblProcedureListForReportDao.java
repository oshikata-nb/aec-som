/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport;

import java.util.List;

/**
 * BeforehandMeltLblProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface BeforehandMeltLblProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<BeforehandMeltLblProcedureListForReport>
	 */
	List<BeforehandMeltLblProcedureListForReport> getProcedureReportList(
			BeforehandMeltLblListConditionForReport condition);
}
