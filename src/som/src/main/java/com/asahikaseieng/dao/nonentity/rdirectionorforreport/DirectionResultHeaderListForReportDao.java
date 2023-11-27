/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirectionorforreport;

import java.util.List;

/**
 * DirectionResultHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionResultHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultHeaderListForReport.class;

	/** ARGSアノテーション getHeaderReportList */
	String getHeaderReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionResultHeaderListForReport>
	 */
	List<DirectionResultHeaderListForReport> getHeaderReportList(DirectionResultListConditionForReport condition
	);
}
