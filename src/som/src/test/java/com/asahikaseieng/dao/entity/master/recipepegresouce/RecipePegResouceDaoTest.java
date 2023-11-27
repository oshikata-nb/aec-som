/*
 * Created on Mon May 18 10:37:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.recipepegresouce;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipePegResouceDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipePegResouceDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(RecipePegResouceDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public RecipePegResouceDaoTest(final String testname) {
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

		RecipePegResouce lhs = new RecipePegResouce();
		RecipePegResouce rhs = new RecipePegResouce();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
