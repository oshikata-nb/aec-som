/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport;

import java.util.List;

/**
 * BeforehandMeltLblDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface BeforehandMeltLblDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<BeforehandMeltLblDetailListForReport> getDetailReportList(
			final BeforehandMeltLblListConditionForReport condition);
}
