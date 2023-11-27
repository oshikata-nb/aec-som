/*
 * Created on Thu Jan 22 17:16:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tmpdeptoffsetgroupdata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TmpdeptOffsetGroupDataDaoクラスのテストケース
 * @author kanri-user
 */
public final class TmpdeptOffsetGroupDataDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(TmpdeptOffsetGroupDataDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TmpdeptOffsetGroupDataDaoTest(final String testname) {
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

		TmpdeptOffsetGroupData lhs = new TmpdeptOffsetGroupData();
		TmpdeptOffsetGroupData rhs = new TmpdeptOffsetGroupData();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
