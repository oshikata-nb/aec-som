/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.companybank;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CompanyBankListForComboboxesDaoクラスのテストケース
 * @author t0011036
 */
public final class CompanyBankListForComboboxesDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CompanyBankListForComboboxesDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CompanyBankListForComboboxesDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CompanyBankListForComboboxesDao_init.xls");

		/* getListForComboboxes 実行 */
		List<CompanyBankListForComboboxes> list = dao.getListForComboboxes();

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"CompanyBankListForComboboxesDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("COMPANY");

		list = dao.getListForComboboxes();
		assertEquals(0, list.size());
	}
}
