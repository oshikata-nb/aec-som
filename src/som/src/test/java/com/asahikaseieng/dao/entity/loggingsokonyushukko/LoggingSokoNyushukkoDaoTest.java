/*
 * Created on Wed Feb 04 16:10:47 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.loggingsokonyushukko;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoggingSokoNyushukkoDaoクラスのテストケース
 * @author kanri-user
 */
public final class LoggingSokoNyushukkoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(LoggingSokoNyushukkoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LoggingSokoNyushukkoDaoTest(final String testname) {
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

		LoggingSokoNyushukko lhs = new LoggingSokoNyushukko();
		LoggingSokoNyushukko rhs = new LoggingSokoNyushukko();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
