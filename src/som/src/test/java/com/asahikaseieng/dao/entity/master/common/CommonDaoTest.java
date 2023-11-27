/*
 * Created on Mon Jan 19 17:30:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.common;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CommonDaoクラスのテストケース
 * @author t0011036
 */
public final class CommonDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.master.common.CommonDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public CommonDaoTest(final String testname) {
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
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("CommonDao_init.xls");

		/* getEntity 実行 */
		Common bean = dao.getEntity("ORDER_PICTURE");

		/* 検証用データ読み込み */
		DataSet expected = readXls("CommonDao_expected.xls", "getEntity");

		/* 取得内容 検証 */
		assertEquals(expected, bean);

		/* データが取得できない場合 */
		bean = null;
		bean = dao.getEntity("9999999999");
		assertNull(bean);
	}
}
