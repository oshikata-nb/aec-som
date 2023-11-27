/*
 * Created on Tue May 19 19:11:51 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.salesinoutrecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalesInoutRecordDaoクラスのテストケース
 * @author kanri-user
 */
public final class SalesInoutRecordDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(SalesInoutRecordDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public SalesInoutRecordDaoTest(final String testname) {
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

		SalesInoutRecord lhs = new SalesInoutRecord();
		SalesInoutRecord rhs = new SalesInoutRecord();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
