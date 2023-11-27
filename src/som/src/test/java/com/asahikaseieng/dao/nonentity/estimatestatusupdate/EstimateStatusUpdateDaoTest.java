/*
 * Created on 2009/09/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatestatusupdate;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EstimateStatusUpdateDaoクラスのテストケース
 * @author t0011036
 */
public final class EstimateStatusUpdateDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public EstimateStatusUpdateDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * updateStatusのテスト
	 */
	public void testUpdateStatusTx() {
		EstimateStatusUpdate lhs = new EstimateStatusUpdate();
		EstimateStatusUpdate rhs = new EstimateStatusUpdate();

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
	private void setValue(final EstimateStatusUpdate bean) {
		bean.setEstimateNo("ESTIMATE_NO001");
		bean.setConsecutiveNo(new BigDecimal("1"));
	}
}
