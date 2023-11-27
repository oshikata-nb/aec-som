/*
 * Created on 2009/08/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionorderforreport;

import java.util.List;

/**
 * DirectionOrderProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionOrderProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionOrderProcedureListForReport>
	 */
	List<DirectionOrderProcedureListForReport> getProcedureReportList(
			final DirectionOrderListConditionForReport condition);
}
