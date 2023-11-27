/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetailcheck;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RemarkDetailCheckDaoクラスのテストケース
 * @author kanri-user
 */
public final class RemarkDetailCheckDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RemarkDetailCheckDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * testGetRemarkDetailCheckTxのテスト
	 */
	public void testGetRemarkDetailCheckTx() {
		RemarkDetailCheck lhs = new RemarkDetailCheck();
		RemarkDetailCheck rhs = new RemarkDetailCheck();

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
	private void setValue(final RemarkDetailCheck bean) {
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER001");
	}
}
