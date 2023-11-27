/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.arealist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * AreaListDaoクラスのテストケース
 * @author t0011036
 */
public final class AreaListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private AreaListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public AreaListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("AreaListDao_init.xls");

		AreaListPagerCondition condition = new AreaListPagerCondition();
		condition.setSrhAreaCd("AREA001");

		/* getList 実行 */
		List<AreaList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("AreaListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("AREA");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
