/*
 * Created on Thu Jan 22 13:17:39 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.eraserheaderdetail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EraserHeaderDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class EraserHeaderDetailDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(EraserHeaderDetailDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public EraserHeaderDetailDaoTest(final String testname) {
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

		EraserHeaderDetail lhs = new EraserHeaderDetail();
		EraserHeaderDetail rhs = new EraserHeaderDetail();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
