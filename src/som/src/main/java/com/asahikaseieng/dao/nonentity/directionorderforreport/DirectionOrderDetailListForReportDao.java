/*
 * Created on 2009/08/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionorderforreport;

import java.util.List;

/**
 * DirectionOrderDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionOrderDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionOrderDetailListForReport>
	 */
	List<DirectionOrderDetailListForReport> getDetailReportList(
			final DirectionOrderListConditionForReport condition);
}
