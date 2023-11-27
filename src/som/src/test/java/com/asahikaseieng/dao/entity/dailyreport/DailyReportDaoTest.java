/*
 * Created on Thu Feb 26 10:16:14 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dailyreport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DailyReportDaoクラスのテストケース
 * @author kanri-user
 */
public final class DailyReportDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(DailyReportDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DailyReportDaoTest(final String testname) {
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

		DailyReport lhs = new DailyReport();
		DailyReport rhs = new DailyReport();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
