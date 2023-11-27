/*
 * Created on Fri Dec 07 15:34:23 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.area;

import java.awt.geom.Area;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * AreaDaoクラスのテストケース
 * @author t0011036
 */
public final class AreaDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(AreaDaoTest.class);

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public AreaDaoTest(final String testname) {
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

		Area lhs = new Area();
		Area rhs = new Area();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}
}
