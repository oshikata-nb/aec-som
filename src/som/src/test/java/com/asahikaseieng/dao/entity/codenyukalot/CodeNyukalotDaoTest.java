/*
 * Created on Wed Feb 04 16:08:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.codenyukalot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CodeNyukalotDaoクラスのテストケース
 * @author kanri-user
 */
public final class CodeNyukalotDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(CodeNyukalotDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public CodeNyukalotDaoTest(final String testname) {
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

		CodeNyukalot lhs = new CodeNyukalot();
		CodeNyukalot rhs = new CodeNyukalot();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
