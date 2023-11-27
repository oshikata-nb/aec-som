/*
 * Created on Fri Jan 23 13:58:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.names;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * NamesDaoクラスのテストケース
 * @author t0011036
 */
public final class NamesDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(NamesDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public NamesDaoTest(final String testname) {
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

		Names lhs = new Names();
		Names rhs = new Names();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
