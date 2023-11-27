/*
 * Created on Thu Jan 22 11:01:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionheader;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DirectionHeaderDaoクラスのテストケース
 * @author t0011036
 */
public final class DirectionHeaderDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(DirectionHeaderDaoTest.class);

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DirectionHeaderDaoTest(final String testname) {
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

		DirectionHeader lhs = new DirectionHeader();
		DirectionHeader rhs = new DirectionHeader();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	public void testGetEntryTx() {
		DirectionHeader bean = dao.getEntity(new BigDecimal(1),
			"TESTDIRECTION001");
		dao.update(bean);
	}
}
