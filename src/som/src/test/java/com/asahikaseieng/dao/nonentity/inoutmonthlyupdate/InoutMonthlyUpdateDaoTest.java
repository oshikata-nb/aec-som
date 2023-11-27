/*
 * Created on 2009/07/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmonthlyupdate;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InoutMonthlyUpdateDaoクラスのテストケース
 * @author t0011036
 */
public final class InoutMonthlyUpdateDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InoutMonthlyUpdateDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getInoutCountのテスト
	 */
	public void testGetInoutCountTx() {
		InoutMonthlyUpdate lhs = new InoutMonthlyUpdate();
		InoutMonthlyUpdate rhs = new InoutMonthlyUpdate();

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
	private void setValue(final InoutMonthlyUpdate bean) {
		bean.setCnt(new BigDecimal("1"));
	}
}
