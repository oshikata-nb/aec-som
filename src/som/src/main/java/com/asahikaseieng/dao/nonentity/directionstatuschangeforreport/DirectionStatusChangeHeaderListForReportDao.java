/*
 * Created on 2009/08/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionstatuschangeforreport;

import java.util.List;

/**
 * DirectionStatusChangeHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionStatusChangeHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeHeaderListForReport.class;

	/** ARGSアノテーション getHeaderReportList */
	String getHeaderReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 *@param condition condition
	 * @return List
	 */
	List<DirectionStatusChangeHeaderListForReport> getHeaderReportList(DirectionStatusChangeListConditionForReport condition
	);
}
