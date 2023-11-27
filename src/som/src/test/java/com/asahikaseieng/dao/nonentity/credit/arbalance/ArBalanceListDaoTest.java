/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalance;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ArBalanceListDaoクラスのテストケース
 * @author t0011036
 */
public final class ArBalanceListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ArBalanceListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ArBalanceListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getSearchListのテスト
	 */
	public void testGetSearchListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("ArBalanceListDao_init.xls");

		ArBalancePagerCondition condition = new ArBalancePagerCondition();
		condition.setSrhAccruedDebitDivi("DIVI01");
		condition.setSrhCreditAmountDivi("DIV01");
		condition.setSrhNormalFlg(true);
		condition.setSrhSectionCd("SECTION01");
		condition.setSrhTantoCd("TANTO01");
		condition.setSrhTargetMonth("01");
		condition.setSrhTempClosingFlg(true);
		condition.setSrhVenderCd("VENDER01");

		/* getSearchList 実行 */
		List<ArBalanceList> list = dao.getSearchList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ArBalanceListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ACCOUNTS");

		list = dao.getSearchList(condition);
		assertEquals(0, list.size());
	}
}
