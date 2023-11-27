/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.area;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AreaListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class AreaForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private AreaForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public AreaForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("AreaListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<AreaForAutoComplete> list = dao.getSearchList("AREA_CD001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls("AreaListForAutoCompleteDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("AREA");

		list = dao.getSearchList("AREA_CD001", "50");
		assertEquals(0, list.size());
	}
}
