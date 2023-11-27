/*
 * Created on 2009/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimateupdate;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EstimateUpdateDaoクラスのテストケース
 * @author t0011036
 */
public final class EstimateUpdateDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public EstimateUpdateDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * updateのテスト
	 */
	public void testUpdateTx() {
		EstimateUpdate lhs = new EstimateUpdate();
		EstimateUpdate rhs = new EstimateUpdate();

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
	private void setValue(final EstimateUpdate bean) {
		bean.setEstimateNo("ESTIMATE_NO001");
		bean.setConsecutiveNo(new BigDecimal("1"));
	}
}
