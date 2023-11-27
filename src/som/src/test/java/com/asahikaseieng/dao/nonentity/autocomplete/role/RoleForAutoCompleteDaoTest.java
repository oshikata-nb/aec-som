/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.role;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RoleListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class RoleForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RoleForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RoleForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RoleListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<RoleForAutoComplete> list = dao.getSearchList("ROLE001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls("RoleListForAutoCompleteDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ROLE");

		list = dao.getSearchList("ROLE001", "50");
		assertEquals(0, list.size());
	}
}
