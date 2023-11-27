/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.artrans;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ArTransJournalListDaoクラスのテストケース
 * @author t0011036
 */
public final class ArTransJournalListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ArTransJournalListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ArTransJournalListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ArTransJournalListDao_init.xls");

		String denYmd = "200907%";

		/* getList 実行 */
		List<ArTransJournalList> list = dao.getList(denYmd);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ArTransJournalListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("TRANS_JOURNAL");

		list = dao.getList(denYmd);
		assertEquals(0, list.size());
	}
}
