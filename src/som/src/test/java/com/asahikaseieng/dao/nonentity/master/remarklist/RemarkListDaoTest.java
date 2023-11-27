/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarklist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RemarkListDaoクラスのテストケース
 * @author kanri-user
 */
public final class RemarkListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private RemarkListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RemarkListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("RemarkListDao_init.xls");

		RemarkListPagerCondition condition = new RemarkListPagerCondition();
		condition.setSrhVenderDivision("TS");
		condition.setSrhVenderCd("VENDER001");

		/* getList 実行 */
		List<RemarkList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("RemarkListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("VENDER");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
