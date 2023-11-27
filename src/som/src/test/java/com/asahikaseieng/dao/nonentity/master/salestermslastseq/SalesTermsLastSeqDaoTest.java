/*
 * Created on 2009/05/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslastseq;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalesTermsLastSeqDaoクラスのテストケース
 * @author t0011036
 */
public final class SalesTermsLastSeqDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public SalesTermsLastSeqDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getLastSeqのテスト
	 */
	public void testGetLastSeqTx() {
		SalesTermsLastSeq lhs = new SalesTermsLastSeq();
		SalesTermsLastSeq rhs = new SalesTermsLastSeq();

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
	private void setValue(final SalesTermsLastSeq bean) {
		bean.setLastSeq(new BigDecimal("1"));
	}
}
