/*
 * Created on 2009/08/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionstatuschangeforreport;

import java.util.List;

/**
 * DirectionStatusChangeDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionStatusChangeDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<DirectionStatusChangeDetailListForReport> getDetailReportList(DirectionStatusChangeListConditionForReport condition
	);
}
