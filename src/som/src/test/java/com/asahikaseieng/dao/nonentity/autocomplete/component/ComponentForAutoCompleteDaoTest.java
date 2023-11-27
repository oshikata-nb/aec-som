/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.component;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ComponentListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class ComponentForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ComponentForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ComponentForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ComponentListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<ComponentForAutoComplete> list = dao.getSearchList("S100", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"ComponentListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("COMPONENT");

		list = dao.getSearchList("S100", "50");
		assertEquals(0, list.size());
	}
}
