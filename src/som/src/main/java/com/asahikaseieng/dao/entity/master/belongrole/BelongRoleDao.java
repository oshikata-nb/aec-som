/*
 * Created on Wed Feb 04 09:44:27 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.belongrole;

/**
 * BelongRoleDaoインターフェース.
 * @author kanri-user
 */
public interface BelongRoleDao {

	/** BEANアノテーション. */
	Class BEAN = BelongRole.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean BelongRole
	 * @return Insert件数
	 */
	int insert(BelongRole bean);

	/**
	 * Update.
	 * @param bean BelongRole
	 * @return Update件数
	 */
	int update(BelongRole bean);

	/**
	 * Delete.
	 * @param bean BelongRole
	 * @return Delete件数
	 */
	int delete(BelongRole bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ORGANIZATION_CD,POST_ID";

	/**
	 * エンティティ取得.
	 * @param organizationCd organizationCd
	 * @param postId postId
	 * @return BelongRole
	 */
	BelongRole getEntity(String organizationCd, java.math.BigDecimal postId);
}
