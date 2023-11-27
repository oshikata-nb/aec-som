/*
 * Created on Wed Apr 29 11:49:04 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.transjournal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TransJournalDaoクラスのテストケース
 * @author t0011036
 */
public final class TransJournalDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(TransJournalDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TransJournalDaoTest(final String testname) {
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

		TransJournal lhs = new TransJournal();
		TransJournal rhs = new TransJournal();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
