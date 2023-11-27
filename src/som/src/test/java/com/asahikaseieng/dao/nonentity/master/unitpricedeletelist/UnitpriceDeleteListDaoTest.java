/*
 * Created on 2009/05/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricedeletelist;

import java.math.BigDecimal;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * UnitpriceDeleteListDaoクラスのテストケース
 * @author t0011036
 */
public final class UnitpriceDeleteListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private UnitpriceDeleteListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public UnitpriceDeleteListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * deleteListのテスト
	 */
	public void testDeleteListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("UnitpriceDeleteListDao_init.xls");

		String venderDivision = "SI";
		String venderCd = "VENDER001";
		String itemCd = "ITEM001";
		BigDecimal version = new BigDecimal("1");

		/* getList 実行 */
		int cnt = dao.deleteList(venderDivision, venderCd, itemCd, version);

		/* 検証用データ読み込み */
		DataSet expected = readXls("UnitpriceDeleteListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, cnt);

		/* データが取得できない場合 */
		deleteTable("UNITPRICE");

		cnt = dao.deleteList(venderDivision, venderCd, itemCd, version);
		assertEquals(0, cnt);
	}
}
