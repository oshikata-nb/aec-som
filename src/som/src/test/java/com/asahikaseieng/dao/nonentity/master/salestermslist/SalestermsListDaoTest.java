/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalestermsListDaoクラスのテストケース
 * @author t0011036
 */
public final class SalestermsListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private SalesTermsListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public SalestermsListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("SalesTermsListDao_init.xls");

		SalesTermsListPagerCondition condition = new SalesTermsListPagerCondition();
		condition.setSrhDeliveryCd("DELIVERY_CD001");
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhVenderCd("VENDER_CD001");

		/* getList 実行 */
		List<SalesTermsList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("SalesTermsListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("SALES_TERMS");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
