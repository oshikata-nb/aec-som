/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * VenderDetailDaoクラスのテストケース
 * @author kanri-user
 */
public final class VenderDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public VenderDetailDaoTest(final String testname) {
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
		VenderDetail lhs = new VenderDetail();
		VenderDetail rhs = new VenderDetail();

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
	private void setValue(final VenderDetail bean) {
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER001");
		bean.setVenderName1("SOM");
		bean.setVenderName2("AEC");
	}
}
