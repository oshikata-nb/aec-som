/*
 * Created on Wed Feb 04 17:44:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.role;

/**
 * RoleDaoインターフェース.
 * @author t0011036
 */
public interface RoleDao {

	/** BEANアノテーション. */
	Class BEAN = Role.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Role
	 * @return Insert件数
	 */
	int insert(Role bean);

	/**
	 * Update.
	 * @param bean Role
	 * @return Update件数
	 */
	int update(Role bean);

	/**
	 * Delete.
	 * @param bean Role
	 * @return Delete件数
	 */
	int delete(Role bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ROLE_ID";

	/**
	 * エンティティ取得.
	 * @param roleId roleId
	 * @return Role
	 */
	Role getEntity(final java.math.BigDecimal roleId);
}
