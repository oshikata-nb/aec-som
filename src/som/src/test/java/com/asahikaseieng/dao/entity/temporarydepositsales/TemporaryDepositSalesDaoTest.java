/*
 * Created on Thu Jan 22 17:27:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositsales;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TemporaryDepositSalesDaoクラスのテストケース
 * @author kanri-user
 */
public final class TemporaryDepositSalesDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(TemporaryDepositSalesDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TemporaryDepositSalesDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * エンティティのテスト.
	 */
	public void testEntityTx() {

		TemporaryDepositSales lhs = new TemporaryDepositSales();
		TemporaryDepositSales rhs = new TemporaryDepositSales();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
