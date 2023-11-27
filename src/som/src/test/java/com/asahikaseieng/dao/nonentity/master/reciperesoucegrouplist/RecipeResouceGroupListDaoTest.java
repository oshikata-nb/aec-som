/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipeResouceGroupListDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipeResouceGroupListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RecipeResouceGroupListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipeResouceGroupListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RecipeResouceGroupDetailListDao_init.xls");

		RecipeResouceGroupListPagerCondition condition = new RecipeResouceGroupListPagerCondition();
		condition.setSrhResouceGroupCd("RESOURCE001");

		/* getList 実行 */
		List<RecipeResouceGroupList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"RecipeResouceGroupDetailListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("RESOURCE_RESOUCE_GROUP");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
