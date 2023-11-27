/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.names;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * NamesListForComboboxesDaoクラスのテストケース
 * @author t0011036
 */
public final class NamesListForComboboxesDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private NamesListForComboboxesDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public NamesListForComboboxesDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListForComboboxesのテスト
	 */
	public void testGetListForComboboxesTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("NamesListForComboboxesDao_init.xls");

		/* getListForComboboxes 実行 */
		List<NamesListForComboboxes> list = dao.getListForComboboxes("TANA");

		/* 検証用データ読み込み */
		DataSet expected = readXls("NamesListForComboboxesDao_expected.xls",
			"getListForComboboxes");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("NAMES");

		list = dao.getListForComboboxes("TANA");
		assertEquals(0, list.size());
	}
}
