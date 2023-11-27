/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ComponentListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class ComponentListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ComponentListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ComponentListForReportDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ComponentListForReportDao_init.xls");

		ComponentListConditionForReport condition = new ComponentListConditionForReport();
		condition.setSrhComponentCd("COMPONENT_CD001");

		/* getList 実行 */
		List<ComponentListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ComponentListForReportDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("COMPONENT");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
