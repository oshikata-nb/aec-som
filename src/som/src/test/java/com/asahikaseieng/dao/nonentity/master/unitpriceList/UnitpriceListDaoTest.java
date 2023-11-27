/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpriceList;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceList;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceListDao;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceListPagerCondition;
import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * UnitpriceListDaoクラスのテストケース
 * @author kanri-user
 */
public final class UnitpriceListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private UnitpriceListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public UnitpriceListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("UnitpriceListDao_init.xls");

		UnitpriceListPagerCondition condition = new UnitpriceListPagerCondition();
		condition.setSrhVenderDivision("TS");
		condition.setSrhVenderCd("VENDER001");
		condition.setSrhItemCd("ITEM001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY001");

		/* getList 実行 */
		List<UnitpriceList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("UnitpriceListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("UNITPRICE");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
