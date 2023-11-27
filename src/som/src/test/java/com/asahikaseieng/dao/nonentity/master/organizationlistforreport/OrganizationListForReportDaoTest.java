/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OrganizationListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class OrganizationListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OrganizationListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OrganizationListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OrganizationListForReportDao_init.xls");

		OrganizationListConditionForReport condition = new OrganizationListConditionForReport();
		condition.setSrhOrganizationCd("ORGANIZATION_CD001");

		/* getListForReport 実行 */
		List<OrganizationListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OrganizationListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ORGANIZATION");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
