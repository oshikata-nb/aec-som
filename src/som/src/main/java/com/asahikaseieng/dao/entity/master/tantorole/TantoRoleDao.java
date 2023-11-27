/*
 * Created on Fri Feb 06 14:36:19 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.tantorole;

/**
 * TantoRoleDaoインターフェース.
 * @author t0011036
 */
public interface TantoRoleDao {

	/** BEANアノテーション. */
	Class BEAN = TantoRole.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TantoRole
	 * @return Insert件数
	 */
	int insert(TantoRole bean);

	/**
	 * Update.
	 * @param bean TantoRole
	 * @return Update件数
	 */
	int update(TantoRole bean);

	/**
	 * Delete.
	 * @param bean TantoRole
	 * @return Delete件数
	 */
	int delete(TantoRole bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ROLE_ID,TANTO_CD";

	/**
	 * エンティティ取得.
	 * @param roleId roleId
	 * @param tantoCd tantoCd
	 * @return TantoRole
	 */
	TantoRole getEntity(final java.math.BigDecimal roleId, String tantoCd);
}
