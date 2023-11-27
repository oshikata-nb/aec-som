/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlist;

import java.util.List;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.seasar.extension.dataset.DataSet;

/**
 * BumonListDaoクラスのテストケース
 * @author t0011036
 */
public final class BumonListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BumonListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BumonListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BumonListDao_init.xls");

		BumonListPagerCondition condition = new BumonListPagerCondition();
		condition.setSrhSectionCd("SECTION001");

		/* getList 実行 */
		List<BumonList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BumonListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BUMON");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
