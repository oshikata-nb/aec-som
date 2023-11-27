/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belonglist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BelongListDaoクラスのテストケース
 * @author t0011036
 */
public final class BelongListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BelongListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BelongListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BelongListDao_init.xls");

		BelongListPagerCondition condition = new BelongListPagerCondition();
		condition.setSrhOrganizationCd("ORGANIZATION_CD001");
		condition.setSrhTantoCd("TANTO_CD001");
		condition.setSrhPostId("POST_ID001");

		/* getList 実行 */
		List<BelongList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BelongListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BELONG");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
