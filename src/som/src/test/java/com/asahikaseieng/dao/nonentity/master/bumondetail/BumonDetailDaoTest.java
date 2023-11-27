/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumondetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BumonDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class BumonDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BumonDetailDaoTest(final String testname) {
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
		BumonDetail lhs = new BumonDetail();
		BumonDetail rhs = new BumonDetail();

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
	private void setValue(final BumonDetail bean) {
		bean.setSectionCd("SECTION001");
		bean.setSectionName("NAME001");
	}
}
