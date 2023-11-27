/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyreport;


/**
 * DailyReportHeaderListDaoクラス
 * @author fml
 */
public interface DailyReportHeaderListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.dailyreport.DailyReportHeaderList.class;

	/** ARGSアノテーション getDailyReportHeaderList */
	String getHeaderList_ARGS = "condition";

	/**
	 * DailyReportHeaderListメソッド
	 * 
	 * @param condition condition
	 * @return DailyReportHeaderList[]
	 */
	DailyReportHeaderList[] getHeaderListEntity(final DailyReportListPagerCondition condition);
}
