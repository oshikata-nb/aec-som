/*
 * Created on Thu Jan 22 15:18:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inventorycount;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InventoryCountDaoクラスのテストケース
 * @author t0011036
 */
public final class InventoryCountDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(InventoryCountDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public InventoryCountDaoTest(final String testname) {
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

		InventoryCount lhs = new InventoryCount();
		InventoryCount rhs = new InventoryCount();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
