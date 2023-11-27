/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyreport.directionheaderupdate;

/**
 * DailyReportHeaderListUpdateDaoクラス
 * @author fml
 */
public interface DirectionHeaderUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.dailyreport.directionheaderupdate.DirectionHeaderUpdate.class;

	/** ARGSアノテーション update */
	//String update_ARGS = "bean";

	/** ARGSアノテーション update */
	String update_ARGS = "productionDate, productionLine";

	/**
	 * updateメソッド
	 * 
	 * @param bean DirectionHeaderUpdate
	 * @return 更新件数
	 */
	//int update(DirectionHeaderUpdate bean);

	/**
	 * updateメソッド
	 * 
	 * @param productionDate String
	 * @param productionLine String
	 * @return 更新件数
	 */
	int update(final String productionDate, final String productionLine);
}
