/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucedetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceDetailDaoクラスのテストケース
 * @author kanri-user
 */
public final class RecipeResouceDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipeResouceDetailDaoTest(final String testname) {
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
		RecipeResouceDetail lhs = new RecipeResouceDetail();
		RecipeResouceDetail rhs = new RecipeResouceDetail();

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
	private void setValue(final RecipeResouceDetail bean) {
		bean.setResouceCd("RESOURCE001");
	}
}
