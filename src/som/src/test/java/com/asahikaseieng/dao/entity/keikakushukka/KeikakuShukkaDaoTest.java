/*
 * Created on Wed Feb 04 16:10:23 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakushukka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * KeikakuShukkaDaoクラスのテストケース
 * @author kanri-user
 */
public final class KeikakuShukkaDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(KeikakuShukkaDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public KeikakuShukkaDaoTest(final String testname) {
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

		KeikakuShukka lhs = new KeikakuShukka();
		KeikakuShukka rhs = new KeikakuShukka();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
