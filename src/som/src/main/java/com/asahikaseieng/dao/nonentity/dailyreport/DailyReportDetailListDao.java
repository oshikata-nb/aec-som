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
public interface DailyReportDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.dailyreport.DailyReportDetailList.class;

	/** ARGSアノテーション getDailyReportDetailList */
	String getDetailList_ARGS = "condition";

	/** ARGSアノテーション getDailyReportDetailList */
	String getDetailListItem_ARGS = "condition";

	/**
	 * 所属マスタ削除処理
	 * 
	 * @param bean 所属マスタ用Bean
	 * @return 削除件数
	 */
	int delete(DailyReportDetailList bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PRODUCTION_LINE,PRODUCTION_DATE,TANTO_DIVISION,TANTO_CD";

	/**
	 * エンティティ取得.
	 * @param productionLine String
	 * @param productionDate java.sql.Timestamp
	 * @param tantoDivision java.math.BigDecimal
	 * @param tantoCd String
	 * @return DailyReportDetailList
	 */
	// DailyReportDetailList getEntity(String productionLine, java.sql.Timestamp
	// productionDate,
	// java.math.BigDecimal tantoDivision, String tantoCd);
	/**
	 * DailyReportDetailListメソッド
	 * 
	 * @param condition condition
	 * @return DailyReportDetailList[]
	 */
	DailyReportDetailList[] getDetailListEntity(
			final DailyReportListPagerCondition condition);

	/**
	 * DailyReportDetailListメソッド
	 * 
	 * @param condition condition
	 * @return DailyReportDetailList[]
	 */
	DailyReportDetailList[] getDetailListItemEntity(
			final DailyReportListPagerCondition condition);
}
