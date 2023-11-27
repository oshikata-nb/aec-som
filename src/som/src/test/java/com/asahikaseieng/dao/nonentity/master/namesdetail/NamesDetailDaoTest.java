/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.namesdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * NamesDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class NamesDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public NamesDetailDaoTest(final String testname) {
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
		NamesDetail lhs = new NamesDetail();
		NamesDetail rhs = new NamesDetail();

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
	private void setValue(final NamesDetail bean) {
		bean.setNameDivision("DIVISION01");
		bean.setNameCd("NAME_CD01");
		bean.setName01("NAME01");
	}
}
