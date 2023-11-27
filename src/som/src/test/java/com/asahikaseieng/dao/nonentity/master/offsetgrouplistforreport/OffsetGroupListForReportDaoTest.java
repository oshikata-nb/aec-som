/*
 * Created on 2009/08/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OffsetGroupListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class OffsetGroupListForReportDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OffsetGroupListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OffsetGroupListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OffsetGroupListForReportDao_init.xls");

		OffsetGroupListConditionForReport condition = new OffsetGroupListConditionForReport();
		condition.setSrhOffsetGroupCd("OFFSET_GROUP_CD001");

		/* getList 実行 */
		List<OffsetGroupListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OffsetGroupListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("OFFSET_GROUP");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
