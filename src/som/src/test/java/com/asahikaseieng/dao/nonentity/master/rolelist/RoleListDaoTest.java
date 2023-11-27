/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RoleListDaoクラスのテストケース
 * @author t0011036
 */
public final class RoleListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RoleListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RoleListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RoleListDao_init.xls");

		RoleListPagerCondition condition = new RoleListPagerCondition();
		condition.setSrhRoleId("1");

		/* getList 実行 */
		List<RoleList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("RoleListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ROLE");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
