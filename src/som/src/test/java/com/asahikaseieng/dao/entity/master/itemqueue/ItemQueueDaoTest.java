/*
 * Created on Thu Jan 22 16:04:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemqueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemQueueDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemQueueDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ItemQueueDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ItemQueueDaoTest(final String testname) {
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

		ItemQueue lhs = new ItemQueue();
		ItemQueue rhs = new ItemQueue();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
