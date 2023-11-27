/*
 * Created on Tue Dec 11 10:53:42 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemcategory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ItemCategoryDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemCategoryDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ItemCategoryDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ItemCategoryDaoTest(final String testname) {
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

		ItemCategory lhs = new ItemCategory();
		ItemCategory rhs = new ItemCategory();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
