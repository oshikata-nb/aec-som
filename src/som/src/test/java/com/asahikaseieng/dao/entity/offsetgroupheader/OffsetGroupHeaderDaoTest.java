/*
 * Created on Fri Jan 23 14:16:34 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.offsetgroupheader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OffsetGroupHeaderDaoクラスのテストケース
 * @author t0011036
 */
public final class OffsetGroupHeaderDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(OffsetGroupHeaderDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public OffsetGroupHeaderDaoTest(final String testname) {
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

		OffsetGroupHeader lhs = new OffsetGroupHeader();
		OffsetGroupHeader rhs = new OffsetGroupHeader();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
