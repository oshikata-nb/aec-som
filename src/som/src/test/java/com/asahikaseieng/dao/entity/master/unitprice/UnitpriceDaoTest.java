/*
 * Created on Wed Jul 25 10:04:58 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.unitprice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * UnitpriceDetailDaoクラスのテストケース
 * @author a1020630
 */
public final class UnitpriceDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(UnitpriceDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public UnitpriceDaoTest(final String testname) {
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

		Unitprice lhs = new Unitprice();
		Unitprice rhs = new Unitprice();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
