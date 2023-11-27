/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipsalesforreport;

import java.util.List;

/**
 * SlipSalesListForReportDaoクラス
 * @author kanri-user
 */
public interface SlipSalesListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListForReport.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<SlipSalesListForReport>
	 */
	List<SlipSalesListForReport> getSearchList(
			SlipSalesListConditionForReport condition);
}
