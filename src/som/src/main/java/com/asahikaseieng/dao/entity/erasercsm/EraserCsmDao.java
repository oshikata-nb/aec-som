/*
 * Created on Thu Jan 22 13:17:18 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.erasercsm;

/**
 * EraserCsmDaoインターフェース.
 * @author t0011036
 */
public interface EraserCsmDao {

	/** BEANアノテーション. */
	Class BEAN = EraserCsm.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean EraserCsm
	 * @return Insert件数
	 */
	int insert(EraserCsm bean);

	/**
	 * Update.
	 * @param bean EraserCsm
	 * @return Update件数
	 */
	int update(EraserCsm bean);

	/**
	 * Delete.
	 * @param bean EraserCsm
	 * @return Delete件数
	 */
	int delete(EraserCsm bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DATA_TYPE,SLIP_NO";

	/**
	 * エンティティ取得.
	 * @param dataType dataType
	 * @param slipNo slipNo
	 * @return EraserCsm
	 */
	EraserCsm getEntity(final java.math.BigDecimal dataType, final String slipNo);
}
