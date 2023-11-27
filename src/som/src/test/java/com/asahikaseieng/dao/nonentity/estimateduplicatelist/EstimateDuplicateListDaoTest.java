/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimateduplicatelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EstimateDuplicateListDaoクラスのテストケース
 * @author t0011036
 */
public final class EstimateDuplicateListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private EstimateDuplicateListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public EstimateDuplicateListDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getDuplicateのテスト
	 */
	public void testGetDuplicateTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("EstimateDuplicateListDao_init.xls");

		/* getDuplicateList 実行 */
		List<EstimateDuplicateList> list = dao.getDuplicateList(
			"ESTIMATE_NO001", "BALANCE_CD001", "ITEM_CD00", "2009/01/01",
			"2009/01/01");

		/* 検証用データ読み込み */
		DataSet expected = readXls("EstimateDuplicateListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ESTIMATE");

		list = dao.getDuplicateList("ESTIMATE_NO001", "BALANCE_CD001",
			"ITEM_CD00", "2009/01/01", "2009/01/01");
		assertEquals(0, list.size());
	}
}
