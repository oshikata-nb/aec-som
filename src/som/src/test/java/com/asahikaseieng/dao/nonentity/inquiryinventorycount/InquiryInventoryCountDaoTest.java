/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinventorycount;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InquiryInventoryCountDaoクラスのテストケース
 * @author t0011036
 */
public final class InquiryInventoryCountDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InquiryInventoryCountDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getInventoryCountのテスト
	 */
	public void testGetInventoryCountTx() {
		InquiryInventoryCount lhs = new InquiryInventoryCount();
		InquiryInventoryCount rhs = new InquiryInventoryCount();

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
	private void setValue(final InquiryInventoryCount bean) {
		bean.setInventoryCount(new BigDecimal("1"));
	}
}
