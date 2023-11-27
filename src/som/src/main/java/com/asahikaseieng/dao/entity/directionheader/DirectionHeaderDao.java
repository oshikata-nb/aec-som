/*
 * Created on Thu Jan 22 11:01:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionheader;

/**
 * DirectionHeaderDaoインターフェース.
 * @author t0011036
 */
public interface DirectionHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = DirectionHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionHeader
	 * @return Insert件数
	 */
	int insert(DirectionHeader bean);

	/**
	 * Update.
	 * @param bean DirectionHeader
	 * @return Update件数
	 */
	int update(DirectionHeader bean);

	/**
	 * Delete.
	 * @param bean DirectionHeader
	 * @return Delete件数
	 */
	int delete(DirectionHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DIRECTION_DIVISION,DIRECTION_NO";

	/**
	 * エンティティ取得.
	 * @param directionDivision directionDivision
	 * @param directionNo directionNo
	 * @return DirectionHeader
	 */
	DirectionHeader getEntity(final java.math.BigDecimal directionDivision,
			final String directionNo);
}
