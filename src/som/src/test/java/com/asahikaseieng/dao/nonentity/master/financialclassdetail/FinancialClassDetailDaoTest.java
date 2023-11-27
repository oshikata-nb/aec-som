/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclassdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * FinancialClassDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class FinancialClassDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public FinancialClassDetailDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		FinancialClassDetail lhs = new FinancialClassDetail();
		FinancialClassDetail rhs = new FinancialClassDetail();

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
	private void setValue(final FinancialClassDetail bean) {
		bean.setFinancialClassCd("CD001");
		bean.setFinancialClassName("NAME001");
	}
}
