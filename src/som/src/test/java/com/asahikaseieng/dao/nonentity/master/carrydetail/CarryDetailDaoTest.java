/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrydetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CarryDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class CarryDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CarryDetailDaoTest(final String testname) {
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
		CarryDetail lhs = new CarryDetail();
		CarryDetail rhs = new CarryDetail();

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
	private void setValue(final CarryDetail bean) {
		bean.setCarryCd("CARRY001");
		bean.setCarryName1("NAME001");
	}
}
