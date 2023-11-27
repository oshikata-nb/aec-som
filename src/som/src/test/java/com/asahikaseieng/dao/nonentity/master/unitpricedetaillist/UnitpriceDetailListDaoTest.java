/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricedetaillist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * UnitpriceDetailListDaoクラスのテストケース
 * @author kanri-user
 */
public final class UnitpriceDetailListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private UnitpriceDetailListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public UnitpriceDetailListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("UnitpriceDetailListDao_init.xls");

		String venderDivision = "SI";
		String venderCd = "VENDER001";
		String itemCd = "ITEM001";
		BigDecimal version = new BigDecimal("1");

		/* getList 実行 */
		List<UnitpriceDetailList> list = dao.getList(venderDivision, venderCd,
			itemCd, version);

		/* 検証用データ読み込み */
		DataSet expected = readXls("UnitpriceDetailListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("UNITPRICE");

		list = dao.getList(venderDivision, venderCd, itemCd, version);
		assertEquals(0, list.size());
	}
}
