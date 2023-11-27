/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelistdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BalanceListDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class BalanceListDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BalanceListDetailDaoTest(final String testname) {
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
		BalanceListDetail lhs = new BalanceListDetail();
		BalanceListDetail rhs = new BalanceListDetail();

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
	private void setValue(final BalanceListDetail bean) {
		bean.setParentBalanceCd("BALANCE001");
		bean.setVenderCd1("VENDER_CD001");
		bean.setVenderCd2("VENDER_CD002");
		bean.setVenderCd3("VENDER_CD003");
		bean.setVenderCd4("VENDER_CD004");
		bean.setVenderCd5("VENDER_CD005");
	}
}
