/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoginListDaoクラスのテストケース
 * @author t0011036
 */
public final class LoginListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LoginListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LoginListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("LoginListDao_init.xls");

		LoginListPagerCondition condition = new LoginListPagerCondition();
		condition.setSrhTantoCd("TANTO_CD001");

		/* getList 実行 */
		List<LoginList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("LoginListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOGIN");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
