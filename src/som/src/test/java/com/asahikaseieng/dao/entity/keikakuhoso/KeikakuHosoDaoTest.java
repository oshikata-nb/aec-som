/*
 * Created on Wed Feb 04 16:09:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakuhoso;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * KeikakuHosoDaoクラスのテストケース
 * @author kanri-user
 */
public final class KeikakuHosoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(KeikakuHosoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public KeikakuHosoDaoTest(final String testname) {
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

		KeikakuHoso lhs = new KeikakuHoso();
		KeikakuHoso rhs = new KeikakuHoso();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
