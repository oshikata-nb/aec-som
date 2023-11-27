/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsduplicatelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalesTermsDuplicateListDaoクラスのテストケース
 * @author t0011036
 */
public final class SalesTermsDuplicateListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private SalesTermsDuplicateListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public SalesTermsDuplicateListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getDuplicateListのテスト
	 */
	public void testGetDuplicateListTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("SalesTermsDuplicateListDao_init.xls");

		String deliveryCd = "DELIVERY_CD001";
		String balanceCd = "BALANCE_CD001";
		String itemCd = "ITEM_CD001";

		/* getList 実行 */
		List<SalesTermsDuplicateList> list = dao.getDuplicateList(deliveryCd,
			balanceCd, itemCd);

		/* 検証用データ読み込み */
		DataSet expected = readXls("SalesTermsDuplicateListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("SALES_TERMS");

		list = dao.getDuplicateList(deliveryCd, balanceCd, itemCd);
		assertEquals(0, list.size());
	}
}
