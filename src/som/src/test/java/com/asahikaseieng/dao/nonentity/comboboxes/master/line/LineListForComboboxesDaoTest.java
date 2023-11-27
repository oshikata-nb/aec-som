/*
 * Created on 2009/05/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.line;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LineListForComboboxesDaoクラスのテストケース
 * @author t0011036
 */
public final class LineListForComboboxesDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LineListForComboboxesDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LineListForComboboxesDaoTest(final String testname) {
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
		readXlsAllReplaceDb("LineListForComboboxesDao_init.xls");

		/* getListForComboboxes 実行 */
		List<LineListForComboboxes> list = dao.getListForComboboxes();

		/* 検証用データ読み込み */
		DataSet expected = readXls("LineListForComboboxesDao_expected.xls",
			"getListForComboboxes");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LINE");

		list = dao.getListForComboboxes();
		assertEquals(0, list.size());
	}
}
