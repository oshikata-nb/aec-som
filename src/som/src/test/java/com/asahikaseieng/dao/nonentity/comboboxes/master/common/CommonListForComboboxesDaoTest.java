/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.common;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CommonListForComboboxesDaoクラスのテストケース
 * @author t0011036
 */
public final class CommonListForComboboxesDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CommonListForComboboxesDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CommonListForComboboxesDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CommonListForComboboxesDao_init.xls");

		/* getListForComboboxes 実行 */
		List<CommonListForComboboxes> list = dao.getListForComboboxes();

		/* 検証用データ読み込み */
		DataSet expected = readXls("CommonListForComboboxesDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("COMMON");

		list = dao.getListForComboboxes();
		assertEquals(0, list.size());
	}
}
