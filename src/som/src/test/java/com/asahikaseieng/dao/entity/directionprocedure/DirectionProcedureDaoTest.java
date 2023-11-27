/*
 * Created on Thu Jan 22 13:14:55 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionprocedure;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DirectionProcedureDaoクラスのテストケース
 * @author t0011036
 */
public final class DirectionProcedureDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(DirectionProcedureDaoTest.class);

	private DirectionProcedureDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DirectionProcedureDaoTest(final String testname) {
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

		DirectionProcedure lhs = new DirectionProcedure();
		DirectionProcedure rhs = new DirectionProcedure();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	public void testListTx() {
		String[] name = {"23", "25", "54", "55", "56"};
		List<DirectionProcedure> list = dao
				.getListForWater("S0907210004", name);
		assertFalse(list.isEmpty());
		log.info(list.size());

	}

	/**
	 * daoを設定します。
	 * @param dao dao
	 */
	public void setDao(final DirectionProcedureDao dao) {
		this.dao = dao;
	}
}
