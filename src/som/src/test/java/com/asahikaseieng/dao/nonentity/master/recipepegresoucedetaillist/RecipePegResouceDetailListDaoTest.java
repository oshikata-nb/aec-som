/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipePegResouceDetailListDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipePegResouceDetailListDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RecipePegResouceDetailListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipePegResouceDetailListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RecipePegResouceDetailListDao_init.xls");

		String resouceCd = "RESOURCE001";

		/* getList 実行 */
		List<RecipePegResouceDetailList> list = dao.getList(resouceCd);

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"RecipePegResouceDetailListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("RESOURCE_PEG_RESOUCE");

		list = dao.getList(resouceCd);
		assertEquals(0, list.size());
	}
}
