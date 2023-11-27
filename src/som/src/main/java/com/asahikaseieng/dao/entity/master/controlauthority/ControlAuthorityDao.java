/*
 * Created on Fri Mar 27 09:29:10 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.controlauthority;

/**
 * ControlAuthorityDaoインターフェース.
 * @author t0011036
 */
public interface ControlAuthorityDao {

	/** BEANアノテーション. */
	Class BEAN = ControlAuthority.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ControlAuthority
	 * @return Insert件数
	 */
	int insert(ControlAuthority bean);

	/**
	 * Update.
	 * @param bean ControlAuthority
	 * @return Update件数
	 */
	int update(ControlAuthority bean);

	/**
	 * Delete.
	 * @param bean ControlAuthority
	 * @return Delete件数
	 */
	int delete(ControlAuthority bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CONTROL_ID,MENU_ID,ROLE_ID,TAB_ID";

	/**
	 * エンティティ取得.
	 * @param controlId controlId
	 * @param menuId menuId
	 * @param roleId roleId
	 * @param tabId tabId
	 * @return ControlAuthority
	 */
	ControlAuthority getEntity(java.math.BigDecimal controlId,
			java.math.BigDecimal menuId, java.math.BigDecimal roleId,
			java.math.BigDecimal tabId);
}
