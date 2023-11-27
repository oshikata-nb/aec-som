/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LoginListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class LoginListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LoginListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LoginListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("LoginListForReportDao_init.xls");

		LoginListConditionForReport condition = new LoginListConditionForReport();
		condition.setSrhOrganizationCd("ORGANIZATION_CD001");
		condition.setSrhTantoCd("TANTO_CD001");
		condition.setSrhPostId("1");
		condition.setSrhLoginTantoCd("LOGIN_TANTO_CD001");
		condition.setSrhLoginAdminFlg("1");
		condition.setSrhNoBelong(null);

		/* getListForReport 実行 */
		List<LoginListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("LoginListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOGIN");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
