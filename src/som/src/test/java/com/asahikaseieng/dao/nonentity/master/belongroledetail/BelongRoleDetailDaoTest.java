/*
 * Created on 2009/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongroledetail;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BelongRoleDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class BelongRoleDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BelongRoleDetailDaoTest(final String testname) {
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
		BelongRoleDetail lhs = new BelongRoleDetail();
		BelongRoleDetail rhs = new BelongRoleDetail();

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
	private void setValue(final BelongRoleDetail bean) {
		bean.setOrganizationCd("ORGANIZATION_CD001");
		bean.setOrganizationName("部署名称００１");
		bean.setPostId(new BigDecimal("1"));
		bean.setPostName("役職名称００１");
		bean.setRoleId(new BigDecimal("1"));
		bean.setRoleName("ロール名称００１");
	}
}
