/*
 * Created on 2007/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.assistance.authority;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.authority.Authority;

/**
 * 権限ロジック interface.
 * @author jbd
 */
public interface AuthorityLogic {

	/**
	 * ガジェット権限List取得メソッド.
	 * @param tantoCd 担当者コード
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return List<Authority>
	 */
	List<Authority> getGadgetAuthorityList(final String tantoCd,
			final String organizationCd, final BigDecimal postId);

	/**
	 * 担当者に紐づくロールList取得メソッド.
	 * @param tantoCd 担当者コード
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return String[] 担当者に紐づくロールList
	 */
	String[] getTantoRoleList(final String tantoCd,
			final String organizationCd, final BigDecimal postId);

	/**
	 * 組織・役職に紐づくロールList取得メソッド.
	 * @param tantoCd 担当者コード
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return String[] 組織・役職に紐づくロールList
	 */
	String[] getBelongRoleList(final String tantoCd,
			final String organizationCd, final BigDecimal postId);
}
