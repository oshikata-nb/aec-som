/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetail;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RemarkDetailDaoクラスのテストケース
 * @author kanri-user
 */
public final class RemarkDetailDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RemarkDetailDaoTest(final String testname) {
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
		RemarkDetail lhs = new RemarkDetail();
		RemarkDetail rhs = new RemarkDetail();

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
	private void setValue(final RemarkDetail bean) {
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER001");
		bean.setVenderName1("SOM");
	}
}
