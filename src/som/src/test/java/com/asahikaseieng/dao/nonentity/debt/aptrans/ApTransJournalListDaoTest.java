/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.aptrans;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ApTransJournalListDaoクラスのテストケース
 * @author t0011036
 */
public final class ApTransJournalListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ApTransJournalListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ApTransJournalListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ApTransJournalListDao_init.xls");

		String denYmd = "200907%";

		/* getList 実行 */
		List<ApTransJournalList> list = dao.getList(denYmd);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ApTransJournalListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("TRANS_JOURNAL");

		list = dao.getList(denYmd);
		assertEquals(0, list.size());
	}
}
