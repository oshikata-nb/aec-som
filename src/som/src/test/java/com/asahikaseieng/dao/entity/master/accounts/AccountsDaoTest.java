/*
 * Created on Wed Dec 05 16:03:17 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.accounts;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * AccountsDaoクラスのテストケース
 * @author t0011036
 */
public final class AccountsDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(AccountsDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public AccountsDaoTest(final String testname) {
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

		Accounts lhs = new Accounts();
		Accounts rhs = new Accounts();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
