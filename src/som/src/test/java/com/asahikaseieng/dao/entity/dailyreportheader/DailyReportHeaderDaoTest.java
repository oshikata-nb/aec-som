/*
 * Created on Thu Feb 26 10:15:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dailyreportheader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DailyReportHeaderDaoクラスのテストケース
 * @author kanri-user
 */
public final class DailyReportHeaderDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(DailyReportHeaderDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DailyReportHeaderDaoTest(final String testname) {
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

		DailyReportHeader lhs = new DailyReportHeader();
		DailyReportHeader rhs = new DailyReportHeader();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
