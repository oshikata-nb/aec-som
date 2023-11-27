/*
 * Created on 2009/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MgrecipeBumonDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class MgrecipeBumonDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public MgrecipeBumonDetailDaoTest(final String testname) {
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
		MgrecipeBumonDetail lhs = new MgrecipeBumonDetail();
		MgrecipeBumonDetail rhs = new MgrecipeBumonDetail();

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
	private void setValue(final MgrecipeBumonDetail bean) {
		bean.setSectionCd("SECTION001");
		bean.setSectionName("NAME001");
	}
}
