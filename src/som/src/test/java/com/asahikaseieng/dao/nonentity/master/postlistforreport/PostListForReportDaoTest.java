/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlistforreport;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PostListForReportDaoクラスのテストケース
 * @author t0011036
 */
public final class PostListForReportDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private PostListForReportDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PostListForReportDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getListForReportのテスト
	 */
	public void testGetListForReportTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("PostListForReportDao_init.xls");

		PostListConditionForReport condition = new PostListConditionForReport();
		condition.setSrhPostId("1");

		/* getListForReport 実行 */
		List<PostListForReport> list = dao.getListForReport(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("PostListForReportDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("POST");

		list = dao.getListForReport(condition);
		assertEquals(0, list.size());
	}
}
