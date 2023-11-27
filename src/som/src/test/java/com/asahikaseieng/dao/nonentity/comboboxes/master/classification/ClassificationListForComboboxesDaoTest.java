/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.classification;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ClassificationListForComboboxesDaoクラスのテストケース
 * @author t0011036
 */
public final class ClassificationListForComboboxesDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ClassificationListForComboboxesDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ClassificationListForComboboxesDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ClassificationListForComboboxesDao_init.xls");

		/* getListForComboboxes 実行 */
		List<ClassificationListForComboboxes> list = dao.getListForComboboxes(
			new BigDecimal("2"), BigDecimal.ZERO);

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"ClassificationListForComboboxesDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CLASSIFICATION");

		list = dao.getListForComboboxes(new BigDecimal("2"), BigDecimal.ZERO);
		assertEquals(0, list.size());
	}
}
