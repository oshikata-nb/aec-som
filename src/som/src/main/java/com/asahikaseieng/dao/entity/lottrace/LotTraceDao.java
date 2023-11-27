/*
 * Created on Thu Jan 22 20:04:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.lottrace;

/**
 * LotTraceDaoインターフェース.
 * @author t0011036
 */
public interface LotTraceDao {

	/** BEANアノテーション. */
	Class BEAN = LotTrace.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean LotTrace
	 * @return Insert件数
	 */
	int insert(LotTrace bean);

	/**
	 * Update.
	 * @param bean LotTrace
	 * @return Update件数
	 */
	int update(LotTrace bean);

	/**
	 * Delete.
	 * @param bean LotTrace
	 * @return Delete件数
	 */
	int delete(LotTrace bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "";

	/**
	 * エンティティ取得.
	 * @return LotTrace
	 */
	LotTrace getEntity();
}
