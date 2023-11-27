/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RoleListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class RoleListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RoleListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RoleListForReportDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListForReportのテスト
	 */
	public void testGetListForReportTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("RoleListForReportDao_init.xls");

		RoleListConditionForReport condition = new RoleListConditionForReport();
		condition.setSrhRoleId("1");

		/* getList 実行 */
		List<RoleListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("RoleListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ROLE");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
