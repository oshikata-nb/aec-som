/*
 * Created on Thu Jan 22 13:14:55 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionprocedure;

import java.util.List;

/**
 * DirectionProcedureDaoインターフェース.
 * @author t0011036
 */
public interface DirectionProcedureDao {

	/** BEANアノテーション. */
	Class BEAN = DirectionProcedure.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionProcedure
	 * @return Insert件数
	 */
	int insert(DirectionProcedure bean);

	/**
	 * Update.
	 * @param bean DirectionProcedure
	 * @return Update件数
	 */
	int update(DirectionProcedure bean);

	/**
	 * Delete.
	 * @param bean DirectionProcedure
	 * @return Delete件数
	 */
	int delete(DirectionProcedure bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DIRECTION_DIVISION,DIRECTION_NO,STEP_NO";

	/**
	 * エンティティ取得.
	 * @param directionDivision directionDivision
	 * @param directionNo directionNo
	 * @param stepNo stepNo
	 * @return DirectionProcedure
	 */
	DirectionProcedure getEntity(final java.math.BigDecimal directionDivision,
			final String directionNo, final java.math.BigDecimal stepNo);

	/** ARGSアノテーション getListForWater(). */
	// String getListForWater_QUERY = "DIRECTION_DIVISION= 1 AND DIRECTION_NO= ?
	// AND OPERATION_CD IN (?) ORDER BY SEQ";
	String getListForWater_ARGS = "directionNo,operationCd";

	/**
	 * リスト取得（水関連)
	 * @param directionNo directionNo
	 * @param operationCd operationCd
	 * @return List<DirectionProcedure> .
	 */
	List<DirectionProcedure> getListForWater(final String directionNo,
			final String[] operationCd);
}
