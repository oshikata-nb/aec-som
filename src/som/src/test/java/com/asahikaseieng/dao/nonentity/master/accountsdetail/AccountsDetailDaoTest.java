/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountsdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AccountsDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class AccountsDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public AccountsDetailDaoTest(final String testname) {
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
		AccountsDetail lhs = new AccountsDetail();
		AccountsDetail rhs = new AccountsDetail();

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
	private void setValue(final AccountsDetail bean) {
		bean.setAccountsCd("ACCOUNTS01");
		bean.setAccountsName("現金");
		bean.setTaxationDivision("01");
	}
}
