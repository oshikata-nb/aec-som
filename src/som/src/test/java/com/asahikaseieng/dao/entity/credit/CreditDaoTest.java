/*
 * Created on Fri Jun 19 11:01:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.credit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CreditDaoクラスのテストケース
 * @author t0011036
 */
public final class CreditDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(CreditDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public CreditDaoTest(final String testname) {
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

		Credit lhs = new Credit();
		Credit rhs = new Credit();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
