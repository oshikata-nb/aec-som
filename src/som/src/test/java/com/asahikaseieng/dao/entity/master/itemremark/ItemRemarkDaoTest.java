/*
 * Created on Thu Jan 22 18:29:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemremark;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ItemRemarkDaoクラスのテストケース
 * @author t0011036
 */
public final class ItemRemarkDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ItemRemarkDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ItemRemarkDaoTest(final String testname) {
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

		ItemRemark lhs = new ItemRemark();
		ItemRemark rhs = new ItemRemark();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
