/*
 * Created on 2007/04/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.authority;

import java.math.BigDecimal;
import java.util.List;

/**
 * AuthorityRoleDaoクラス
 * @author jbd
 */
public interface AuthorityRoleDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.authority.AuthorityRole.class;

	/** ARGSアノテーション getTantoRoleList */
	String getTantoRoleList_ARGS = "tantoCd, organizationCd, postId";

	/**
	 * 担当ロールList取得メソッド
	 * @param tantocd tantocd
	 * @param organizationCd organizationCd
	 * @param postId postId
	 * @return List<AuthorityRole>
	 */
	List<AuthorityRole> getTantoRoleList(final String tantocd,
			final String organizationCd, final BigDecimal postId);

	/** ARGSアノテーション getBelongRoleList */
	String getBelongRoleList_ARGS = "tantoCd, organizationCd, postId";

	/**
	 * 所属ロールList取得メソッド
	 * @param tantocd tantocd
	 * @param organizationCd organizationCd
	 * @param postId postId
	 * @return List<AuthorityRole>
	 */
	List<AuthorityRole> getBelongRoleList(final String tantocd,
			final String organizationCd, final BigDecimal postId);
}
