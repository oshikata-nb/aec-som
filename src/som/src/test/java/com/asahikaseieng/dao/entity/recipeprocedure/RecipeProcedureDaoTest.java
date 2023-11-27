/*
 * Created on Fri Jan 23 15:13:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeprocedure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeProcedureDaoクラスのテストケース
 * @author kanri-user
 */
public final class RecipeProcedureDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(RecipeProcedureDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public RecipeProcedureDaoTest(final String testname) {
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

		RecipeProcedure lhs = new RecipeProcedure();
		RecipeProcedure rhs = new RecipeProcedure();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
