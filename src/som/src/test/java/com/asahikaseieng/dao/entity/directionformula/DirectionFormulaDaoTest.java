/*
 * Created on Thu Jan 22 09:54:30 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionformula;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * DirectionFormulaDaoクラスのテストケース
 * @author t0011036
 */
public final class DirectionFormulaDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(DirectionFormulaDaoTest.class);

	private DirectionFormulaDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public DirectionFormulaDaoTest(final String testname) {
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

		DirectionFormula lhs = new DirectionFormula();
		DirectionFormula rhs = new DirectionFormula();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	public void testListTx() {
		String[] items = {"01777777", "01333333", "01444444"};
		List<DirectionFormula> list = dao.getListForWater("S0907210004",
			new BigDecimal(13), items);
		assertFalse(list.isEmpty());
		log.info(list.size());

	}

	/**
	 * daoを設定します。
	 * @param dao dao
	 */
	public void setDao(final DirectionFormulaDao dao) {
		this.dao = dao;
	}

}
