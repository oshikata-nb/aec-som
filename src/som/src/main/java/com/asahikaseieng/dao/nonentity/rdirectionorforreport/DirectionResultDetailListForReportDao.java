/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirectionorforreport;

import java.util.List;

/**
 * DirectionResultDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface DirectionResultDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<DirectionResultDetailListForReport>
	 */
	List<DirectionResultDetailListForReport> getDetailReportList(
			DirectionResultListConditionForReport condition);
}
