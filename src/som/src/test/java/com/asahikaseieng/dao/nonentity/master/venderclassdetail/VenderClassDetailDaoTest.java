/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderclassdetail;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * VenderClassDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class VenderClassDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public VenderClassDetailDaoTest(final String testname) {
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
		VenderClassDetail lhs = new VenderClassDetail();
		VenderClassDetail rhs = new VenderClassDetail();

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
	private void setValue(final VenderClassDetail bean) {
		bean.setCategoryDivision("1");
		bean.setCategoryName("NAME01");
		bean.setNoteDivision(new BigDecimal("1"));
	}
}
