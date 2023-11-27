/*
 * Created on 2009/08/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionorderforreport;

import java.util.List;

/**
 * DirectionOrderListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionOrderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListForReport.class;

	/** ARGSアノテーション getHeaderReportList */
	String getHeaderReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionOrderListForReport>
	 */
	List<DirectionOrderListForReport> getHeaderReportList(
			final DirectionOrderListConditionForReport condition);
}
