/*
 * Created on Thu Jan 22 17:33:52 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimsales;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * TemporaryClaimSalesDaoクラスのテストケース
 * @author kanri-user
 */
public final class TemporaryClaimSalesDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(TemporaryClaimSalesDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TemporaryClaimSalesDaoTest(final String testname) {
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

		TemporaryClaimSales lhs = new TemporaryClaimSales();
		TemporaryClaimSales rhs = new TemporaryClaimSales();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
