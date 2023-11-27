/*
 * Created on Wed Feb 04 10:01:42 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.reciperesouce;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceDaoクラスのテストケース
 * @author kanri-user
 */
public final class RecipeResouceDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(RecipeResouceDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public RecipeResouceDaoTest(final String testname) {
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

		RecipeResouce lhs = new RecipeResouce();
		RecipeResouce rhs = new RecipeResouce();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
