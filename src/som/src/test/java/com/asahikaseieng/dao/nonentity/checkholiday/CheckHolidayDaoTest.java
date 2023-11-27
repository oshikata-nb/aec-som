/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.checkholiday;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CheckHolidayDaoクラスのテストケース
 * @author t0011036
 */
public final class CheckHolidayDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CheckHolidayDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getCalHolidayのテスト
	 */
	public void testGetCalHolidayTx() {
		CheckHolidayDetail lhs = new CheckHolidayDetail();
		CheckHolidayDetail rhs = new CheckHolidayDetail();

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
	private void setValue(final CheckHolidayDetail bean) {
		bean.setCalendarCd("CAL01");
		bean.setCalHoliday(new BigDecimal("1"));
	}
}
