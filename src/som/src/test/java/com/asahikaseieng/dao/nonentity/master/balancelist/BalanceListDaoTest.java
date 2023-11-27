/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BalanceListDaoクラスのテストケース
 * @author t0011036
 */
public final class BalanceListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BalanceListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BalanceListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BalanceListDao_init.xls");

		BalanceListPagerCondition condition = new BalanceListPagerCondition();
		condition.setSrhBalanceType(new BigDecimal("1"));
		condition.setSrhVenderCd("VENDER_CD001");

		/* getList 実行 */
		List<BalanceList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BalanceListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BALANCE");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
