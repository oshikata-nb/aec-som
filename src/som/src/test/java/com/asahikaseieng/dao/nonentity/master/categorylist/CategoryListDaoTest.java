/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.categorylist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * CategoryListDaoクラスのテストケース
 * @author t0011036
 */
public final class CategoryListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private CategoryListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public CategoryListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("CategoryListDao_init.xls");

		CategoryListPagerCondition condition = new CategoryListPagerCondition();
		condition.setCategoryDivision("DIVISION001");

		/* getList 実行 */
		List<CategoryList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("CategoryListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CATEGORY");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
