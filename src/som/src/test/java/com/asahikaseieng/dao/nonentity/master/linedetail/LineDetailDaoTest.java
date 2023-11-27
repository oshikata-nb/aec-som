/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.linedetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LineDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class LineDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LineDetailDaoTest(final String testname) {
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
		LineDetail lhs = new LineDetail();
		LineDetail rhs = new LineDetail();

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
	private void setValue(final LineDetail bean) {
		bean.setProductionLine("PRODUCTION_LINE01");
		bean.setProductionLineName("NAME01");
	}
}
