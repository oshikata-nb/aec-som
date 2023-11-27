/*
 * Created on Thu Jan 22 18:50:34 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.line;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LineDaoクラスのテストケース
 * @author t0011036
 */
public final class LineDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(LineDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LineDaoTest(final String testname) {
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

		Line lhs = new Line();
		Line rhs = new Line();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
