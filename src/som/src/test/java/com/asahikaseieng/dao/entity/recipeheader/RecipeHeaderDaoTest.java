/*
 * Created on Thu May 07 14:49:29 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeheader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeHeaderDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipeHeaderDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(RecipeHeaderDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public RecipeHeaderDaoTest(final String testname) {
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

		RecipeHeader lhs = new RecipeHeader();
		RecipeHeader rhs = new RecipeHeader();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
