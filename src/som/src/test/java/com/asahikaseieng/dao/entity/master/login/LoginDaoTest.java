/*
 * Created on Wed Feb 04 18:16:43 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoginDaoクラスのテストケース
 * @author t0011036
 */
public final class LoginDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(LoginDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LoginDaoTest(final String testname) {
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

		Login lhs = new Login();
		Login rhs = new Login();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
