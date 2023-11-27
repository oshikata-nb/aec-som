/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.nameslist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * NamesListDaoクラスのテストケース
 * @author t0011036
 */
public final class NamesListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private NamesListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public NamesListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("NamesListDao_init.xls");

		NamesListPagerCondition condition = new NamesListPagerCondition();
		condition.setSrhNameDivision("NAME_DIVISION01");
		condition.setSrhNameCd("NAME_CD01");

		/* getList 実行 */
		List<NamesList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("NamesListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("NAMES");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
