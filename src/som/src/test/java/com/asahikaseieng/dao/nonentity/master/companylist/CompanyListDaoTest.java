/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companylist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CompanyListDaoクラスのテストケース
 * @author t0011036
 */
public final class CompanyListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CompanyListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CompanyListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CompanyListDao_init.xls");

		CompanyListPagerCondition condition = new CompanyListPagerCondition();
		condition.setSrhHomeName1("HOME_NAME1");

		/* getList 実行 */
		List<CompanyList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("CompanyListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("COMPANY");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
