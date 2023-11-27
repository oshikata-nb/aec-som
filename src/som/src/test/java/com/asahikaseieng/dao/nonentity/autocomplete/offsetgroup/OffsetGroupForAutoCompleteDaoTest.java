/*
 * Created on 2009/07/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OffsetGroupForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class OffsetGroupForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OffsetGroupForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OffsetGroupForAutoCompleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getSearchListのテスト
	 */
	public void testGetSearchListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("OffsetGroupListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<OffsetGroupForAutoComplete> list = dao.getSearchList(
			"OFFSET_GROUP_CD001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"OffsetGroupListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("OFFSET_GROUP");

		list = dao.getSearchList("OFFSET_GROUP_CD001", "50");
		assertEquals(0, list.size());
	}
}
