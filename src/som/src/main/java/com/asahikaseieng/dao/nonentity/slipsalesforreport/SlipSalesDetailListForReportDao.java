/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipsalesforreport;

import java.util.List;

/**
 * SlipSalesDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface SlipSalesDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesDetailListForReport.class;

	/** ARGSアノテーション getDetailSearchList */
	String getDetailSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<SlipSalesDetailListForReport>
	 */
	List<SlipSalesDetailListForReport> getDetailSearchList(
			final SlipSalesListConditionForReport condition);
}
