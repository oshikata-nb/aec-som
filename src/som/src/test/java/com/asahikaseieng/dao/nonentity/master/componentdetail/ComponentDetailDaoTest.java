/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ComponentDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ComponentDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ComponentDetailDaoTest(final String testname) {
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
		ComponentDetail lhs = new ComponentDetail();
		ComponentDetail rhs = new ComponentDetail();

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
	private void setValue(final ComponentDetail bean) {
		bean.setComponentCd("COMPONENT001");
		bean.setComponentName("NAME001");
	}
}
