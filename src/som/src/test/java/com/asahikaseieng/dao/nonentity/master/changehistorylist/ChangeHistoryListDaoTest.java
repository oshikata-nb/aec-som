/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.changehistorylist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ChangeHistoryListDaoクラスのテストケース
 * @author t0011036
 */
public final class ChangeHistoryListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ChangeHistoryListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ChangeHistoryListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ChangeHistoryListDao_init.xls");

		BigDecimal menuId = new BigDecimal("60");
		String itemCd = "ITEM001";

		/* getList 実行 */
		List<ChangeHistoryList> list = dao.getList(menuId, itemCd);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ChangeHistoryListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CHANGE_HISTORY");

		list = dao.getList(menuId, itemCd);
		assertEquals(0, list.size());
	}
}
