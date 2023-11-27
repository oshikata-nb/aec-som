/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.callist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CalListDaoクラスのテストケース
 * @author t0011036
 */
public final class CalListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CalListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CalListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CalListDao_init.xls");

		CalListPagerCondition condition = new CalListPagerCondition();
		condition.setSrhCalCd("CAL_CD001");

		/* getList 実行 */
		List<CalList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("CalListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CAL");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
