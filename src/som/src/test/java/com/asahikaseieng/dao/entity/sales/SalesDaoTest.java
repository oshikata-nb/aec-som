/*
 * Created on Thu Jan 29 08:48:41 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sales;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalesDaoクラスのテストケース
 * @author kanri-user
 */
public final class SalesDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(SalesDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public SalesDaoTest(final String testname) {
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

		Sales lhs = new Sales();
		Sales rhs = new Sales();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
