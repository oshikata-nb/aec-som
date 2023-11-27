/*
 * Created on 2007/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.assistance.authority;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.authority.Authority;
import com.asahikaseieng.dao.nonentity.authority.AuthorityDao;
import com.asahikaseieng.dao.nonentity.authority.AuthorityRole;
import com.asahikaseieng.dao.nonentity.authority.AuthorityRoleDao;

/**
 * 権限ロジック 実装クラス.
 * @author jbd
 */
public class AuthorityLogicImpl implements AuthorityLogic {

	private AuthorityDao authorityDao;

	private AuthorityRoleDao authorityRoleDao;

	/**
	 * コンストラクタ.
	 */
	public AuthorityLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Authority> getGadgetAuthorityList(final String tantoCd,
			final String organizationCd, final BigDecimal postId) {

		/* パラメータチェック */
		checkParams(tantoCd);

		/* 担当者・所属に紐づくロールを取得 */
		String[] tantoRoleIds = getTantoRoleList(tantoCd, organizationCd,
			postId);

		String[] belongRoleIds = getBelongRoleList(tantoCd, organizationCd,
			postId);

		/* ロールが何も取れなかった場合は空を返す */
		if (tantoRoleIds.length == 0 && belongRoleIds.length == 0) {
			return new ArrayList<Authority>();
		}

		/* ガジェット権限を取得 */
		return authorityDao.getGadgetAuthorityList(tantoRoleIds, belongRoleIds);
	}

	/**
	 * 担当ロール取得メソッド.
	 * @param tantoCd tantocd
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return 担当者に紐付くロール配列
	 */
	public String[] getTantoRoleList(final String tantoCd,
			final String organizationCd, final BigDecimal postId) {

		List<AuthorityRole> list = authorityRoleDao.getTantoRoleList(tantoCd,
			organizationCd, postId);

		if (list.isEmpty()) {
			return new String[] {};
		}

		return convertStrArray(list);
	}

	/**
	 * 所属ロール取得メソッド.
	 * @param tantoCd tantocd
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return 組織・役職に紐付くロール配列
	 */
	public String[] getBelongRoleList(final String tantoCd,
			final String organizationCd, final BigDecimal postId) {

		List<AuthorityRole> list = authorityRoleDao.getBelongRoleList(tantoCd,
			organizationCd, postId);

		if (list.isEmpty()) {
			return new String[] {};
		}

		return convertStrArray(list);
	}

	/**
	 * パラメータチェック.
	 * @param key String
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final String key) throws IllegalArgumentException {
		if (StringUtils.isEmpty(key)) {
			throw new IllegalArgumentException("Paramater is empty.");
		}
	}

	/**
	 * List<AuthorityRole>のRoleIdをString[]に変換
	 * @param list<AuthorityRole>
	 * @return String[]
	 */
	private String[] convertStrArray(final List<AuthorityRole> list) {
		List<String> newList = new ArrayList<String>();
		for (AuthorityRole bean : list) {
			newList.add(bean.getRoleId().toString());
		}
		return (String[]) newList.toArray(new String[0]);
	}

	/* -------------------- setter -------------------- */

	/**
	 * authorityRoleDaoを設定します。
	 * @param authorityRoleDao AuthorityRoleDao
	 */
	public void setAuthorityRoleDao(final AuthorityRoleDao authorityRoleDao) {
		this.authorityRoleDao = authorityRoleDao;
	}

	/**
	 * authorityDaoを設定します。
	 * @param authorityDao AuthorityDao
	 */
	public void setAuthorityDao(final AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

}
