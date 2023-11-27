/*
 * Created on Fri Jan 23 14:14:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.offsetgroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OffsetGroupDaoクラスのテストケース
 * @author t0011036
 */
public final class OffsetGroupDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(OffsetGroupDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public OffsetGroupDaoTest(final String testname) {
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

		OffsetGroup lhs = new OffsetGroup();
		OffsetGroup rhs = new OffsetGroup();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
