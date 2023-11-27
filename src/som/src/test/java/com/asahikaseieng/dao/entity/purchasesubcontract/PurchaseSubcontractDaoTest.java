/*
 * Created on Fri Jan 23 17:01:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.purchasesubcontract;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PurchaseSubcontractDaoクラスのテストケース
 * @author kanri-user
 */
public final class PurchaseSubcontractDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory
			.getLog(PurchaseSubcontractDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public PurchaseSubcontractDaoTest(final String testname) {
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

		PurchaseSubcontract lhs = new PurchaseSubcontract();
		PurchaseSubcontract rhs = new PurchaseSubcontract();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
