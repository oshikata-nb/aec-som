/*
 * Created on 2022/08/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionfileimport;

/**
 * DirectionHeaderTempDaoインターフェース.
 * @author 
 */
public interface DirectionHeaderTempDao {

	/** BEANアノテーション. */
	Class BEAN = DirectionHeaderTemp.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionHeader
	 * @return Insert件数
	 */
	int insert(DirectionHeaderTemp bean);

	/**
	 * Update.
	 * @param bean DirectionHeader
	 * @return Update件数
	 */
	int update(DirectionHeaderTemp bean);

	/**
	 * Delete.
	 * @param bean DirectionHeader
	 * @return Delete件数
	 */
	int delete(DirectionHeaderTemp bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TEMP_NO,ROW_NUMBER";

	/**
	 * エンティティ取得.
	 * @param tempNo tempNo
	 * @param rowNumber rowNumber
	 * @return DirectionHeaderTemp
	 */
	DirectionHeaderTemp getEntity(final String tempNo, final java.math.BigDecimal rowNumber);
}
