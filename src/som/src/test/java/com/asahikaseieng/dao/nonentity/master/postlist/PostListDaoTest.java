/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * PostListDaoクラスのテストケース
 * @author t0011036
 */
public final class PostListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private PostListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public PostListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("PostListDao_init.xls");

		PostListPagerCondition condition = new PostListPagerCondition();
		condition.setSrhPostId("1");

		/* getList 実行 */
		List<PostList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("PostListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("POST");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
