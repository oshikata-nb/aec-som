/*
 * Created on 2009/02/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceGroupDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipeResouceGroupDetailDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipeResouceGroupDetailDaoTest(final String testname) {
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
		RecipeResouceGroupDetail lhs = new RecipeResouceGroupDetail();
		RecipeResouceGroupDetail rhs = new RecipeResouceGroupDetail();

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
	private void setValue(final RecipeResouceGroupDetail bean) {
		bean.setResouceGroupCd("RESOUCE_GROUP_CD01");
		bean.setResouceGroupName("NAME01");
	}
}
