/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginrolelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoginRoleListDaoクラスのテストケース
 * @author t0011036
 */
public final class LoginRoleListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LoginRoleListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LoginRoleListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getLoginRoleListのテスト
	 */
	public void testGetListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("LoginRoleListDao_init.xls");

		/* getList 実行 */
		List<LoginRoleList> list = dao.getList("TANTO_CD001");

		/* 検証用データ読み込み */
		DataSet expected = readXls("LoginRoleListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOGIN");

		list = dao.getList("TANTO_CD001");
		assertEquals(0, list.size());
	}
}
