/*
 * Created on Thu Jan 22 20:02:59 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.lotinventory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LotInventoryDaoクラスのテストケース
 * @author t0011036
 */
public final class LotInventoryDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(LotInventoryDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LotInventoryDaoTest(final String testname) {
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

		LotInventory lhs = new LotInventory();
		LotInventory rhs = new LotInventory();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
