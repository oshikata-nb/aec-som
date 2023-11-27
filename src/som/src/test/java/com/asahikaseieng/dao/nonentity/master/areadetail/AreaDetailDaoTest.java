/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.areadetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AreaDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class AreaDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public AreaDetailDaoTest(final String testname) {
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
		AreaDetail lhs = new AreaDetail();
		AreaDetail rhs = new AreaDetail();

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
	private void setValue(final AreaDetail bean) {
		bean.setAreaCd("AREA001");
		bean.setAreaName("地区名００１");
	}
}
