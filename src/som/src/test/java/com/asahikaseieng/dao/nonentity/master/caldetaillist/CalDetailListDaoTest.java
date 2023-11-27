/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldetaillist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CalDetailListDaoクラスのテストケース
 * @author t0011036
 */
public final class CalDetailListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CalDetailListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CalDetailListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CalDetailListDao_init.xls");

		/* getList 実行 */
		List<CalDetailList> list = dao.getList("CAL_CD001", new BigDecimal(
				"2009"));

		/* 検証用データ読み込み */
		DataSet expected = readXls("CalDetailListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CAL");

		list = dao.getList("CAL_CD001", new BigDecimal("2009"));
		assertEquals(0, list.size());
	}
}
