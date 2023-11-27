/*
 * Created on Mon Apr 09 13:53:25 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadget;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.dataset.DataSet;

/**
 * GadgetDaoクラスのテストケース
 * @author jbd
 */
public final class GadgetDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(GadgetDaoTest.class);

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.gadget.GadgetDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public GadgetDaoTest(final String testname) {
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

		Gadget lhs = new Gadget();
		Gadget rhs = new Gadget();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
	}

	/**
	 * エンティティのテスト.
	 */
	public void testGetEntityTx() {

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("GadgetDao_init.xls");

		/* データが取得できる場合 */
		Gadget bean = dao.getEntity("001");

		/* 検証用データ読み込み */
		DataSet expected = readXls("GadgetDao_expected.xls", "getEntity");
		/* 取得内容 検証 */
		assertEquals(expected, bean);

		/* データが取得できない場合 */
		bean = dao.getEntity("999");

		assertNull(bean);
	}
}
