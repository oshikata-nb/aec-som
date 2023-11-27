/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.commondetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CommonDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class CommonDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CommonDetailDaoTest(final String testname) {
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
		CommonDetail lhs = new CommonDetail();
		CommonDetail rhs = new CommonDetail();

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
	private void setValue(final CommonDetail bean) {
		bean.setCommonCd("CD001");
		bean.setCommonName("NAME001");
		bean.setCommonValue("VALUE001");
	}
}
