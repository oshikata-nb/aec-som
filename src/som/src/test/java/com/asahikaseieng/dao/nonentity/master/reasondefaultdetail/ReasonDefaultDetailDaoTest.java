/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasondefaultdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ReasonDefaultDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ReasonDefaultDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ReasonDefaultDetailDaoTest(final String testname) {
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
		ReasonDefaultDetail lhs = new ReasonDefaultDetail();
		ReasonDefaultDetail rhs = new ReasonDefaultDetail();

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
	private void setValue(final ReasonDefaultDetail bean) {
		bean.setRyCd("RY_CD001");
		bean.setRyDescription("RY_DESCRIPTION001");
	}
}
