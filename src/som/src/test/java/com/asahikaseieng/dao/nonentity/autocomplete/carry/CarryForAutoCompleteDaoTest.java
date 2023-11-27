/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.carry;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CarryListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class CarryForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CarryForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CarryForAutoCompleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListForAutoCompleteのテスト
	 */
	public void testGetListForAutoCompleteTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("CarryListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<CarryForAutoComplete> list = dao.getSearchList("S100", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls("CarryListForAutoCompleteDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CARRY");

		list = dao.getSearchList("S100", "50");
		assertEquals(0, list.size());
	}
}
