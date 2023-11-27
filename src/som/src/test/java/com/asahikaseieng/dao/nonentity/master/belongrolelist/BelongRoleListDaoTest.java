/*
 * Created on 2009/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongrolelist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * BelongRoleListDaoクラスのテストケース
 * @author t0011036
 */
public final class BelongRoleListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private BelongRoleListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public BelongRoleListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("BelongRoleListDao_init.xls");

		BelongRoleListPagerCondition condition = new BelongRoleListPagerCondition();
		condition.setSrhOrganizationCd("ORGANIZATION_CD001");
		condition.setSrhPostId("POST_ID001");
		condition.setSrhRoleId("ROLE_ID001");

		/* getList 実行 */
		List<BelongRoleList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("BelongRoleListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("BELONG_ROLE");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
