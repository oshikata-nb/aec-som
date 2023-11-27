/*
 * Created on 2009/08/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionstatuschangeforreport;

import java.util.List;

/**
 * DirectionStatusChangeProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionStatusChangeProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<DirectionStatusChangeProcedureListForReport> getProcedureReportList(
			DirectionStatusChangeListConditionForReport condition);
}
