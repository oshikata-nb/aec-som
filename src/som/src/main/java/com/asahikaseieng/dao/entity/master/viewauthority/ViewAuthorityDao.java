/*
 * Created on Fri Mar 27 09:13:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.viewauthority;

/**
 * ViewAuthorityDaoインターフェース.
 * @author t0011036
 */
public interface ViewAuthorityDao {

	/** BEANアノテーション. */
	Class BEAN = ViewAuthority.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ViewAuthority
	 * @return Insert件数
	 */
	int insert(ViewAuthority bean);

	/**
	 * Update.
	 * @param bean ViewAuthority
	 * @return Update件数
	 */
	int update(ViewAuthority bean);

	/**
	 * Delete.
	 * @param bean ViewAuthority
	 * @return Delete件数
	 */
	int delete(ViewAuthority bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "MENU_ID,ROLE_ID,TAB_ID";

	/**
	 * エンティティ取得.
	 * @param menuId menuId
	 * @param roleId roleId
	 * @param tabId tabId
	 * @return ViewAuthority
	 */
	ViewAuthority getEntity(java.math.BigDecimal menuId,
			java.math.BigDecimal roleId, java.math.BigDecimal tabId);
}
