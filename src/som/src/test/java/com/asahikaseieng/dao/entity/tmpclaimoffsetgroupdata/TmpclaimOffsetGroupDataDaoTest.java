/*
 * Created on Thu Jan 22 17:22:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tmpclaimoffsetgroupdata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TmpclaimOffsetGroupDataDaoクラスのテストケース
 * @author kanri-user
 */
public final class TmpclaimOffsetGroupDataDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(TmpclaimOffsetGroupDataDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TmpclaimOffsetGroupDataDaoTest(final String testname) {
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

		TmpclaimOffsetGroupData lhs = new TmpclaimOffsetGroupData();
		TmpclaimOffsetGroupData rhs = new TmpclaimOffsetGroupData();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
