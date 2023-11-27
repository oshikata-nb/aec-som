/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.assistance.authority;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.authority.Authority;

/**
 * 権限ロジック Mockクラス.
 * @author jbd
 */
public class MockAuthorityLogicImpl implements AuthorityLogic {

	/**
	 * コンストラクタ.
	 */
	public MockAuthorityLogicImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public String[] getBelongRoleList(final String tantoCd,
			final String organizationCd, final BigDecimal postId) {

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return new String[] {};
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(organizationCd)) {
			return new String[] {};
		}

		return new String[] {"1", "3", "5", "7", "9"};
	}

	/**
	 * {@inheritDoc}
	 */
	public String[] getTantoRoleList(final String tantoCd,
			final String organizationCd, final BigDecimal postId) {

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return new String[] {};
		}

		return new String[] {"2", "4", "6", "7", "10"};
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Authority> getMenuAuthorityList(final String tantoCd,
			final String organizationCd, final BigDecimal postId) {

		return createList(tantoCd, organizationCd, postId, 5);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Authority> getGadgetAuthorityList(final String tantoCd,
			final String organizationCd, final BigDecimal postId) {

		return createList(tantoCd, organizationCd, postId, 5);
	}

	/**
	 * List<Authority> を作成する.
	 * @param tantoCd 担当者コード
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @param max データ数
	 * @return List<Authority>
	 */
	private List<Authority> createList(final String tantoCd,
			final String organizationCd, final BigDecimal postId, final int max) {
		List<Authority> list = new ArrayList<Authority>();

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return list;
		}

		for (int i = 0; i < 10; i++) {
			list.add(createBean(i));
		}
		return list;
	}

	/**
	 * Authorityを作成する.
	 * @param i インデックス
	 * @return Authority
	 */
	private Authority createBean(final int i) {
		Authority bean = new Authority();
		// bean.setAuthorityKbn("1");
		// bean.setId("" + i);
		return bean;
	}
}
