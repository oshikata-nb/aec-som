/*
 * Created on Tue Apr 21 14:40:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tdataseihinnyuka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * TDataSeihinNyukaDaoクラスのテストケース
 * @author a7710658
 */
public final class TDataSeihinNyukaDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(TDataSeihinNyukaDaoTest.class);

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.tdataseihinnyuka.TDataSeihinNyukaDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public TDataSeihinNyukaDaoTest(final String testname) {
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

		TDataSeihinNyuka lhs = new TDataSeihinNyuka();
		TDataSeihinNyuka rhs = new TDataSeihinNyuka();

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
		log.info(">>> " + lhs.toString());
		TDataSeihinNyuka bean = dao.getEntity("P0310200001");
		assertNotNull(bean);
		bean.setNyukabc("ETO");
		// Timestamp ts = bean.getTsNonyuyoteibi();
		bean.setTsNonyuyoteibi(AecDateUtils.getCurrentTimestamp());
		int ct = dao.insert(bean);
		assertEquals(ct, 1);
	}

	// TODO テスト

	/**
	 * getListのテスト.
	 */
	public void testGetListTx() {
		assertNotNull(dao.getList());
	}

}
