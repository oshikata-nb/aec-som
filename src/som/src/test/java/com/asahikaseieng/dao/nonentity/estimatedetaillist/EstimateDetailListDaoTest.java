/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatedetaillist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EstimateDetailListDaoクラスのテストケース
 * @author t0011036
 */
public final class EstimateDetailListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private EstimateDetailListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public EstimateDetailListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("EstimateDetailListDao_init.xls");

		/* getList 実行 */
		List<EstimateDetailList> list = dao.getList("ESTIMATE_NO001");

		/* 検証用データ読み込み */
		DataSet expected = readXls("EstimateDetailListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ESTIMATE");

		list = dao.getList("ESTIMATE_NO001");
		assertEquals(0, list.size());
	}
}
