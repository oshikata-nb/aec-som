/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belonglistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BelongListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class BelongListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BelongListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BelongListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BelongListDao_init.xls");

		BelongListConditionForReport condition = new BelongListConditionForReport();
		condition.setSrhOrganizationCd("ORGANIZATION_CD001");
		condition.setSrhTantoCd("TANTO_CD001");
		condition.setSrhPostId("1");

		/* getList 実行 */
		List<BelongListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BelongListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BELONG");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
