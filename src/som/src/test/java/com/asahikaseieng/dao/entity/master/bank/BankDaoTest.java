/*
 * Created on Fri Jan 16 10:23:58 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.bank;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * BankDaoクラスのテストケース
 * @author t0011036
 */
public final class BankDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(BankDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public BankDaoTest(final String testname) {
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

		Bank lhs = new Bank();
		Bank rhs = new Bank();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
