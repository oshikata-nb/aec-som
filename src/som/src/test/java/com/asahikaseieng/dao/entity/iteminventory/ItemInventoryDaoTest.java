/*
 * Created on Thu Jan 22 18:23:14 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.iteminventory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemInventoryDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemInventoryDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ItemInventoryDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ItemInventoryDaoTest(final String testname) {
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

		ItemInventory lhs = new ItemInventory();
		ItemInventory rhs = new ItemInventory();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
