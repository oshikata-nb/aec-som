/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongpostlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BelongPostListDaoクラスのテストケース
 * @author t0011036
 */
public final class BelongPostListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BelongPostListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BelongPostListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListのテスト
	 */
	public void testGetListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("BelongPostListListDao_init.xls");

		/* getList 実行 */
		List<BelongPostList> list = dao.getList("1");

		/* 検証用データ読み込み */
		DataSet expected = readXls("BelongPostListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("POST");

		list = dao.getList("1");
		assertEquals(0, list.size());
	}
}
