/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancedetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BalanceDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class BalanceDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BalanceDetailDaoTest(final String testname) {
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
		BalanceDetail lhs = new BalanceDetail();
		BalanceDetail rhs = new BalanceDetail();

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
	private void setValue(final BalanceDetail bean) {
		bean.setBalanceCd("BALANCE001");
		bean.setVenderCd("VENDER001");
	}
}
