/*
 * Created on Thu Jan 22 13:14:41 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directioninspection;

/**
 * DirectionInspectionDaoインターフェース.
 * @author t0011036
 */
public interface DirectionInspectionDao {

	/** BEANアノテーション. */
	Class BEAN = DirectionInspection.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionInspection
	 * @return Insert件数
	 */
	int insert(DirectionInspection bean);

	/**
	 * Update.
	 * @param bean DirectionInspection
	 * @return Update件数
	 */
	int update(DirectionInspection bean);

	/**
	 * Delete.
	 * @param bean DirectionInspection
	 * @return Delete件数
	 */
	int delete(DirectionInspection bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DIRECTION_DIVISION,DIRECTION_NO,LINE_NO,STEP_NO";

	/**
	 * エンティティ取得.
	 * @param directionDivision directionDivision
	 * @param directionNo directionNo
	 * @param lineNo lineNo
	 * @param stepNo stepNo
	 * @return DirectionInspection
	 */
	DirectionInspection getEntity(final java.math.BigDecimal directionDivision,
			final String directionNo, final java.math.BigDecimal lineNo,
			final java.math.BigDecimal stepNo);
}
