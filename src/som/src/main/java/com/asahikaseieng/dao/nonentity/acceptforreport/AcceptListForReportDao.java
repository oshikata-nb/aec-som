/*
 * Created on 2009/08/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.acceptforreport;

import java.util.List;

/**
 * AcceptListForReportDaoクラス
 * @author kanri-user
 */
public interface AcceptListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.acceptforreport.AcceptListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<AcceptListForReport>
	 */
	List<AcceptListForReport> getReportList(
			final AcceptListConditionForReport condition);
}
