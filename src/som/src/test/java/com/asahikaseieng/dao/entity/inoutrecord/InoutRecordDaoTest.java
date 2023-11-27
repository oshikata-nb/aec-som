/*
 * Created on Thu Jan 22 15:02:22 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutrecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InoutRecordDaoクラスのテストケース
 * @author t0011036
 */
public final class InoutRecordDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(InoutRecordDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public InoutRecordDaoTest(final String testname) {
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

		InoutRecord lhs = new InoutRecord();
		InoutRecord rhs = new InoutRecord();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
