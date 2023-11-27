/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.roledetaillist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RoleDetailListDaoクラスのテストケース
 * @author t0011036
 */
public final class RoleDetailListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RoleDetailListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RoleDetailListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getDetailListのテスト
	 */
	public void testGetDetailListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("RoleDetailListDao_init.xls");

		/* getList 実行 */
		List<RoleDetailList> list = dao.getList("1");

		/* 検証用データ読み込み */
		DataSet expected = readXls("RoleDetailListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ROLE");

		list = dao.getList("1");
		assertEquals(0, list.size());
	}
}
