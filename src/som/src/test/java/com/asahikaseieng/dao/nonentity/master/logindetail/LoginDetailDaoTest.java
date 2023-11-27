/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.logindetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoginDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class LoginDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LoginDetailDaoTest(final String testname) {
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
		LoginDetail lhs = new LoginDetail();
		LoginDetail rhs = new LoginDetail();

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
	private void setValue(final LoginDetail bean) {
		bean.setTantoCd("TANTO_CD01");
		bean.setTantoNm("NAME01");
	}
}
