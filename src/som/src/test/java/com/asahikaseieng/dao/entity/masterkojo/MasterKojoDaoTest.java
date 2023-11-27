/*
 * Created on Wed Feb 04 16:12:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterkojo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MasterKojoDaoクラスのテストケース
 * @author kanri-user
 */
public final class MasterKojoDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MasterKojoDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MasterKojoDaoTest(final String testname) {
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

		MasterKojo lhs = new MasterKojo();
		MasterKojo rhs = new MasterKojo();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
