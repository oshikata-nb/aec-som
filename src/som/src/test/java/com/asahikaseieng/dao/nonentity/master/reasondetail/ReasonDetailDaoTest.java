/*
 * Created on 2009/03/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasondetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ReasonDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ReasonDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ReasonDetailDaoTest(final String testname) {
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
		ReasonDetail lhs = new ReasonDetail();
		ReasonDetail rhs = new ReasonDetail();

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
	private void setValue(final ReasonDetail bean) {
		bean.setRyCd("RY_CD001");
		bean.setRyDescription("RY_DESCRIPTION001");
	}
}
