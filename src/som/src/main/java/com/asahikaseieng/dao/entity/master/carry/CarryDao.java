/*
 * Created on Fri Jan 16 16:23:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.carry;

/**
 * CarryDaoインターフェース.
 * @author t0011036
 */
public interface CarryDao {

	/** BEANアノテーション. */
	Class BEAN = Carry.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Carry
	 * @return Insert件数
	 */
	int insert(Carry bean);

	/**
	 * Update.
	 * @param bean Carry
	 * @return Update件数
	 */
	int update(Carry bean);

	/**
	 * Delete.
	 * @param bean Carry
	 * @return Delete件数
	 */
	int delete(Carry bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CARRY_CD";

	/**
	 * エンティティ取得.
	 * @param carryCd carryCd
	 * @return Carry
	 */
	Carry getEntity(final String carryCd);
}
