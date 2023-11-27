/*
 * Created on Thu Jan 22 20:07:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.monthlyinoutrecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * MonthlyInoutRecordDaoクラスのテストケース
 * @author t0011036
 */
public final class MonthlyInoutRecordDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(MonthlyInoutRecordDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public MonthlyInoutRecordDaoTest(final String testname) {
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

		MonthlyInoutRecord lhs = new MonthlyInoutRecord();
		MonthlyInoutRecord rhs = new MonthlyInoutRecord();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
