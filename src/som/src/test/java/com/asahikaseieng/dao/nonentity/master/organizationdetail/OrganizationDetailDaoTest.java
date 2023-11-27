/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OrganizationDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class OrganizationDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OrganizationDetailDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getEntityのテスト
	 */
	public void testGetEntityTx() {
		OrganizationDetail lhs = new OrganizationDetail();
		OrganizationDetail rhs = new OrganizationDetail();

		/* 検索条件設定 */
		setValue(lhs);
		setValue(rhs);

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
	}

	/**
	 * 検索条件設定
	 * @param bean
	 */
	private void setValue(final OrganizationDetail bean) {
		bean.setOrganizationCd("ORGANIZATION01");
		bean.setOrganizationName("NAME01");
	}
}
