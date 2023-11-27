/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.banklist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BankListDaoクラスのテストケース
 * @author t0011036
 */
public final class BankListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BankListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BankListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BankListDao_init.xls");

		BankListPagerCondition condition = new BankListPagerCondition();
		condition.setSrhBankCd("BANK001");
		condition.setSrhBranchCd("BRANCH001");
		condition.setSrhBankMasterCd("BANK_MASTER001");

		/* getList 実行 */
		List<BankList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BankListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BANK");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
