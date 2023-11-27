/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.varidunitprice;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * VaridUnitpriceDaoクラスのテストケース
 * @author kanri-user
 */
public final class VaridUnitpriceDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public VaridUnitpriceDaoTest(final String testname) {
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
		VaridUnitprice lhs = new VaridUnitprice();
		VaridUnitprice rhs = new VaridUnitprice();

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
	private void setValue(final VaridUnitprice bean) {
		bean.setVenderCd("VENDER001");
	}
}
