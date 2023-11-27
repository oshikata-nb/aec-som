/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginallrolelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoginAllRoleListDaoクラスのテストケース
 * @author t0011036
 */
public final class LoginAllRoleListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LoginAllRoleListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LoginAllRoleListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListのテスト
	 */
	public void testGetListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("LoginAllRoleListDao_init.xls");

		/* getList 実行 */
		List<LoginAllRoleList> list = dao.getList();

		/* 検証用データ読み込み */
		DataSet expected = readXls("LoginAllRoleListDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOGIN");

		list = dao.getList();
		assertEquals(0, list.size());
	}
}
