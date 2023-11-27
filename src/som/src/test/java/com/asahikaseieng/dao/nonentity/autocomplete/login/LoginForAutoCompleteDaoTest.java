/*
 * Created on 2009/01/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.login;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoginListForAutoCompleteDaoクラスのテストケース
 * @author t0011036
 */
public final class LoginForAutoCompleteDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LoginForAutoCompleteDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LoginForAutoCompleteDaoTest(final String testname) {
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
		readXlsAllReplaceDb("LoginListForAutoCompleteDao_init.xls");

		/* getListForAutoComplete 実行 */
		List<LoginForAutoComplete> list = dao.getSearchList("TANTO001", "50");

		/* 検証用データ読み込み */
		DataSet expected = readXls("LoginListForAutoCompleteDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOGIN");

		list = dao.getSearchList("TANTO001", "50");
		assertEquals(0, list.size());
	}
}
