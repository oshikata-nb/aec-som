/*
 * Created on 2022/08/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionfileimport;


/**
 * DirectionProcedureTempDaoインターフェース.
 * @author 
 */
public interface DirectionProcedureTempDao {

	/** BEANアノテーション. */
	Class BEAN = DirectionProcedureTemp.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionProcedure
	 * @return Insert件数
	 */
	int insert(DirectionProcedureTemp bean);

	/**
	 * Update.
	 * @param bean DirectionProcedure
	 * @return Update件数
	 */
	int update(DirectionProcedureTemp bean);

	/**
	 * Delete.
	 * @param bean DirectionProcedure
	 * @return Delete件数
	 */
	int delete(DirectionProcedureTemp bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TEMP_NO,ROW_NUMBER";

	/**
	 * エンティティ取得.
	 * @param tempNo tempNo
	 * @param rowNumber rowNumber
	 * @return DirectionProcedureTemp
	 */
	DirectionProcedureTemp getEntity(final String tempNo, final java.math.BigDecimal rowNumber);
}
