/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OperationGroupListForComboboxesDaoクラスのテストケース
 * @author t0011036
 */
public final class OperationGroupListForComboboxesDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OperationGroupListForComboboxesDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OperationGroupListForComboboxesDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OperationGroupListForComboboxesDao_init.xls");

		/* getListForComboboxes 実行 */
		List<OperationGroupListForComboboxes> list = dao.getListForComboboxes();

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"OperationGroupListForComboboxesDao_expected.xls",
			"getListForComboboxes");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("OPERATION_GROUP");

		list = dao.getListForComboboxes();
		assertEquals(0, list.size());
	}
}
