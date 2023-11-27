/*
 * Created on Fri Jan 16 16:23:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.carryfilelayout;

import java.util.List;

/**
 * CarryDaoインターフェース.
 * @author t0011036
 */
public interface CarryFileLayoutDao {

	/** BEANアノテーション. */
	Class BEAN = CarryFileLayout.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Carry
	 * @return Insert件数
	 */
	int insert(CarryFileLayout bean);

	/**
	 * Update.
	 * @param bean Carry
	 * @return Update件数
	 */
	int update(CarryFileLayout bean);

	/**
	 * Delete.
	 * @param bean Carry
	 * @return Delete件数
	 */
	int delete(CarryFileLayout bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CARRY_CD";

	/**
	 * エンティティ取得.
	 * @param carryCd carryCd
	 * @return Carry
	 */
	List<CarryFileLayout> getEntity(final String carryCd);
}
