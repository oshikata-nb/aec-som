/*
 * Created on 2009/09/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.classificationdetail;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ClassificationDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ClassificationDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ClassificationDetailDaoTest(final String testname) {
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
		ClassificationDetail lhs = new ClassificationDetail();
		ClassificationDetail rhs = new ClassificationDetail();

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
	private void setValue(final ClassificationDetail bean) {
		bean.setDataType(BigDecimal.ONE);
		bean.setCategoryDivision("1");
		bean.setDataTotalDivision(BigDecimal.ONE);
	}
}
