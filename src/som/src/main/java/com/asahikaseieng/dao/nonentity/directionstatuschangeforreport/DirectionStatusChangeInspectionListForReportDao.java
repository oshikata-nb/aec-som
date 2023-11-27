/*
 * Created on 2009/08/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionstatuschangeforreport;

import java.util.List;

/**
 * DirectionStatusChangeInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionStatusChangeInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 *@param condition condition
	 * @return List
	 */
	List<DirectionStatusChangeInspectionListForReport> getInspectionReportList(DirectionStatusChangeListConditionForReport condition
	);
}
