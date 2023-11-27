/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquirylocationcount;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InquiryLocationCountDaoクラスのテストケース
 * @author t0011036
 */
public final class InquiryLocationCountDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InquiryLocationCountDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getLocationCountのテスト
	 */
	public void testGetLocationCountTx() {
		InquiryLocationCount lhs = new InquiryLocationCount();
		InquiryLocationCount rhs = new InquiryLocationCount();

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
	private void setValue(final InquiryLocationCount bean) {
		bean.setLocationCount(new BigDecimal("1"));
	}
}
