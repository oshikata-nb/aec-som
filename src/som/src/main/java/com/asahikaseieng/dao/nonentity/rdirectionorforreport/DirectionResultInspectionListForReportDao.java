/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirectionorforreport;

import java.util.List;

/**
 * DirectionResultInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionResultInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionResultInspectionListForReport>
	 */
	List<DirectionResultInspectionListForReport> getInspectionReportList(DirectionResultListConditionForReport condition
	);
}
