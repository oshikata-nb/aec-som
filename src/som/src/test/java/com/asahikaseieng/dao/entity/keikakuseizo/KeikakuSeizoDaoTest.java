/*
 * Created on Wed Feb 04 16:10:11 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakuseizo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * KeikakuSeizoDaoクラスのテストケース
 * @author kanri-user
 */
public final class KeikakuSeizoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(KeikakuSeizoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public KeikakuSeizoDaoTest(final String testname) {
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

		KeikakuSeizo lhs = new KeikakuSeizo();
		KeikakuSeizo rhs = new KeikakuSeizo();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
