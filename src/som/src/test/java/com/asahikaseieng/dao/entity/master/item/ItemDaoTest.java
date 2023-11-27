/*
 * Created on Thu Jan 22 17:06:28 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.item;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ItemDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ItemDaoTest(final String testname) {
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

		Item lhs = new Item();
		Item rhs = new Item();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
