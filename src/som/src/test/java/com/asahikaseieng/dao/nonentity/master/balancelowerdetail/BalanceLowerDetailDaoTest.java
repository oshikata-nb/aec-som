/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelowerdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BalanceLowerDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class BalanceLowerDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BalanceLowerDetailDaoTest(final String testname) {
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
		BalanceLowerDetail lhs = new BalanceLowerDetail();
		BalanceLowerDetail rhs = new BalanceLowerDetail();

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
	private void setValue(final BalanceLowerDetail bean) {
		bean.setBalanceCd("BALANCE001");
	}
}
