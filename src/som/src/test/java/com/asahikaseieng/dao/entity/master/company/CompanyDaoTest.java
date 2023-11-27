/*
 * Created on Mon Jan 19 17:47:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.company;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CompanyDaoクラスのテストケース
 * @author t0011036
 */
public final class CompanyDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(CompanyDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public CompanyDaoTest(final String testname) {
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

		Company lhs = new Company();
		Company rhs = new Company();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
