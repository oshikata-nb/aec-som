/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OrganizationListDaoクラスのテストケース
 * @author t0011036
 */
public final class OrganizationListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OrganizationListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OrganizationListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OrganizationListDao_init.xls");

		OrganizationListPagerCondition condition = new OrganizationListPagerCondition();
		condition.setSrhOrganizationCd("ORGANIZATION001");

		/* getList 実行 */
		List<OrganizationList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OrganizationListDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ORGANIZATION");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
