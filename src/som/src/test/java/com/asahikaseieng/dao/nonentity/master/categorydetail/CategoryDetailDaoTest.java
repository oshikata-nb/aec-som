/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.categorydetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CategoryDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class CategoryDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CategoryDetailDaoTest(final String testname) {
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
		CategoryDetail lhs = new CategoryDetail();
		CategoryDetail rhs = new CategoryDetail();

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
	private void setValue(final CategoryDetail bean) {
		bean.setCategoryDivision("DIVISION001");
		bean.setCategoryCd("CD001");
		bean.setCategoryName("NAME001");
	}
}
