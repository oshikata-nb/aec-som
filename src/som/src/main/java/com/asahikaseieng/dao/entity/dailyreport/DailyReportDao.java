/*
 * Created on Thu Feb 12 18:54:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dailyreport;

/**
 * DailyReportDaoインターフェース.
 * @author kanri-user
 */
public interface DailyReportDao {

	/** BEANアノテーション. */
	Class BEAN = DailyReport.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DailyReport
	 * @return Insert件数
	 */
	int insert(DailyReport bean);

	/**
	 * Update.
	 * @param bean DailyReport
	 * @return Update件数
	 */
	int update(DailyReport bean);

	/**
	 * Delete.
	 * @param bean DailyReport
	 * @return Delete件数
	 */
	int delete(DailyReport bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PRODUCTION_LINE,PRODUCTION_DATE,TANTO_DIVISION,TANTO_CD,DIRECTION_NO";

	/**
	 * エンティティ取得.
	 * @param productionLine productionLine
	 * @param productionDate productionDate
	 * @param tantoDivision tantoDivision
	 * @param tantoCd tantoCd
	 * @param directionNo directionNo
	 * @return DailyReport
	 */
	DailyReport getEntity(
					String productionLine,
					java.sql.Timestamp productionDate,
					java.math.BigDecimal tantoDivision,
					String tantoCd,
					String directionNo);
}
