/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrylist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CarryListDaoクラスのテストケース
 * @author t0011036
 */
public final class CarryListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CarryListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CarryListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CarryListDao_init.xls");

		CarryListPagerCondition condition = new CarryListPagerCondition();
		condition.setSrhCarryCd("CARRY001");

		/* getList 実行 */
		List<CarryList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("CarryListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CARRY");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
