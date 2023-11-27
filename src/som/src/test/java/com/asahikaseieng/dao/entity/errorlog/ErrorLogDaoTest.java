/*
 * Created on Thu Mar 12 11:47:51 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.errorlog;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ErrorLogDaoクラスのテストケース
 * @author kanri-user
 */
public final class ErrorLogDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ErrorLogDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ErrorLogDaoTest(final String testname) {
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

		ErrorLog lhs = new ErrorLog();
		ErrorLog rhs = new ErrorLog();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
