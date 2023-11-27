/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.viewauthoritylist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ViewAuthorityListDaoクラスのテストケース
 * @author t0011036
 */
public final class ViewAuthorityListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ViewAuthorityListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ViewAuthorityListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ViewAuthorityListDao_init.xls");

		String tantoCd = "TANTO001";
		BigDecimal menuId = new BigDecimal("1");
		BigDecimal tabId = new BigDecimal("1");

		/* getList 実行 */
		List<ViewAuthorityList> list = dao.getList(tantoCd, menuId, tabId);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ViewAuthorityListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("VIEW_AUTHORITY");

		list = dao.getList(tantoCd, menuId, tabId);
		assertEquals(0, list.size());
	}
}
