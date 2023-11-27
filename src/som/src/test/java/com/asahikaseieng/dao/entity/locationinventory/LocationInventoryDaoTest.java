/*
 * Created on Thu Jan 22 20:01:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.locationinventory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LocationInventoryDaoクラスのテストケース
 * @author t0011036
 */
public final class LocationInventoryDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(LocationInventoryDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public LocationInventoryDaoTest(final String testname) {
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

		LocationInventory lhs = new LocationInventory();
		LocationInventory rhs = new LocationInventory();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
