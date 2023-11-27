/*
 * Created on Fri Jan 23 14:16:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.offsetgroupdata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OffsetGroupDataDaoクラスのテストケース
 * @author t0011036
 */
public final class OffsetGroupDataDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(OffsetGroupDataDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public OffsetGroupDataDaoTest(final String testname) {
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

		OffsetGroupData lhs = new OffsetGroupData();
		OffsetGroupData rhs = new OffsetGroupData();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
