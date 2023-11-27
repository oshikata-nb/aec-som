/*
 * Created on Thu Feb 12 18:53:43 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dailyreportheader;

/**
 * DailyReportHeaderDaoインターフェース.
 * @author kanri-user
 */
public interface DailyReportHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = DailyReportHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DailyReportHeader
	 * @return Insert件数
	 */
	int insert(DailyReportHeader bean);

	/**
	 * Update.
	 * @param bean DailyReportHeader
	 * @return Update件数
	 */
	int update(DailyReportHeader bean);

	/**
	 * Delete.
	 * @param bean DailyReportHeader
	 * @return Delete件数
	 */
	int delete(DailyReportHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PRODUCTION_LINE,PRODUCTION_DATE,TANTO_DIVISION,TANTO_CD,SEQ";

	/**
	 * エンティティ取得.
	 * @param productionDate productionDate
	 * @param productionLine productionLine
	 * @param seq seq
	 * @param tantoDivision tantoDivision
	 * @param tantoCd tantoCd
	 * @return DailyReportHeader
	 */
	DailyReportHeader getEntity(
					String productionLine,
					java.sql.Timestamp productionDate,
					java.math.BigDecimal tantoDivision,
					String tantoCd,
					java.math.BigDecimal seq);
}
