/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offsetlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OffsetListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class OffsetListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OffsetListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OffsetListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OffsetListForReportDao_init.xls");

		OffsetListConditionForReport condition = new OffsetListConditionForReport();
		condition.setSrhOrganizationCd("ORGANIZATION_CD001");
		condition.setSrhTantoCd("TANTO_CD001");
		condition.setSrhOffsetGrp("OFFSET_GROUP_CD001");

		/* getList 実行 */
		List<OffsetListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OffsetListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("OFFSET_GROUP_HEADER");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
