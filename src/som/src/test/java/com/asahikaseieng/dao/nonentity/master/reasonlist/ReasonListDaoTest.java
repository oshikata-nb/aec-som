/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasonlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ReasonListDaoクラスのテストケース
 * @author t0011036
 */
public final class ReasonListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ReasonListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ReasonListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ReasonListDao_init.xls");

		ReasonListPagerCondition condition = new ReasonListPagerCondition();
		condition.setSrhRyCd("RY001");

		/* getList 実行 */
		List<ReasonList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ReasonListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("REASON");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
