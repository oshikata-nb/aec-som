/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirectionorforreport;

import java.util.List;

/**
 * DirectionResultProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionResultProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionResultProcedureListForReport>
	 */
	List<DirectionResultProcedureListForReport> getProcedureReportList(DirectionResultListConditionForReport condition
	);
}
