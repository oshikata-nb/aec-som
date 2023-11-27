/*
 * Created on Tue Feb 03 10:41:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dataseihinnyuka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DataSeihinNyukaDaoクラスのテストケース
 * @author t0011036
 */
public final class DataSeihinNyukaDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(DataSeihinNyukaDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DataSeihinNyukaDaoTest(final String testname) {
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

		DataSeihinNyuka lhs = new DataSeihinNyuka();
		DataSeihinNyuka rhs = new DataSeihinNyuka();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
