/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclasslist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * FinancialClassListDaoクラスのテストケース
 * @author t0011036
 */
public final class FinancialClassListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private FinancialClassListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public FinancialClassListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("FinancialClassListDao_init.xls");

		FinancialClassListPagerCondition condition = new FinancialClassListPagerCondition();
		condition.setSrhFinancialClassCd("FINANCIAL001");

		/* getList 実行 */
		List<FinancialClassList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("FinancialClassListDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("FINANCIAL_CLASS");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
