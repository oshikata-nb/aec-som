/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.arrivalforreport;

import java.util.List;

/**
 * ArrivalListForReportDaoクラス
 * @author kanri-user
 */
public interface ArrivalListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.arrivalforreport.ArrivalListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<ArrivalListForReport>
	 */
	List<ArrivalListForReport> getReportList(
			ArrivalListConditionForReport condition);
}
