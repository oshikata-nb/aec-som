/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companydetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CompanyDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class CompanyDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CompanyDetailDaoTest(final String testname) {
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
		CompanyDetail lhs = new CompanyDetail();
		CompanyDetail rhs = new CompanyDetail();

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
	private void setValue(final CompanyDetail bean) {
		bean.setCompanyCd("COMPANY001");
		bean.setHomeName1("NAME001");
	}
}
