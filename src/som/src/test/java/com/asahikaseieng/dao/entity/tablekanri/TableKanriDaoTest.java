/*
 * Created on Wed Feb 04 16:12:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tablekanri;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TableKanriDaoクラスのテストケース
 * @author kanri-user
 */
public final class TableKanriDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(TableKanriDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TableKanriDaoTest(final String testname) {
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

		TableKanri lhs = new TableKanri();
		TableKanri rhs = new TableKanri();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
