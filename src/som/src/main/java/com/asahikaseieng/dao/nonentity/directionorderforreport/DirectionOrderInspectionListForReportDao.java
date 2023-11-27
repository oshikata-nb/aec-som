/*
 * Created on 2009/08/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionorderforreport;

import java.util.List;

/**
 * DirectionOrderInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionOrderInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionOrderInspectionListForReport>
	 */
	List<DirectionOrderInspectionListForReport> getInspectionReportList(
			final DirectionOrderListConditionForReport condition);
}
