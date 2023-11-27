/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.operationgroup;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OperationGroupListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class OperationGroupForAutoCompleteDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OperationGroupForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OperationGroupForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OperationGroupListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<OperationGroupForAutoComplete> list = dao.getSearchList(
			"OPERATION_GROUP001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"OperationGroupListForAutoCompleteDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("OPERATION_GROUP");

		list = dao.getSearchList("OPERATION_GROUP001", "50");
		assertEquals(0, list.size());
	}
}
