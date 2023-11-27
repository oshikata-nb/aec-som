/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlogin;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoginLoginDaoクラスのテストケース
 * @author t0011036
 */
public final class LoginLoginDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LoginLoginDaoTest(final String testname) {
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
		LoginLogin lhs = new LoginLogin();
		LoginLogin rhs = new LoginLogin();

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
	private void setValue(final LoginLogin bean) {
		bean.setTantoCd("TANTO_CD01");
		bean.setTantoNm("NAME01");
	}
}
