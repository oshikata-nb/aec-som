/*
 * Created on Mon Mar 09 08:51:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.financialclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * FinancialClassDaoクラスのテストケース
 * @author t0011036
 */
public final class FinancialClassDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(FinancialClassDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public FinancialClassDaoTest(final String testname) {
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

		FinancialClass lhs = new FinancialClass();
		FinancialClass rhs = new FinancialClass();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
