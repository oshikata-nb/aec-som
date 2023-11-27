/*
 * Created on Mon Jan 19 16:30:39 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.changehistory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ChangeHistoryDaoクラスのテストケース
 * @author t0011036
 */
public final class ChangeHistoryDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ChangeHistoryDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ChangeHistoryDaoTest(final String testname) {
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

		ChangeHistory lhs = new ChangeHistory();
		ChangeHistory rhs = new ChangeHistory();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
