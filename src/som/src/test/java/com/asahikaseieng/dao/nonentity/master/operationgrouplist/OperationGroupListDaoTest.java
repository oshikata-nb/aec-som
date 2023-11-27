/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationgrouplist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OperationGroupListDaoクラスのテストケース
 * @author t0011036
 */
public final class OperationGroupListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OperationGroupListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OperationGroupListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("OperationGroupListDao_init.xls");

		OperationGroupListPagerCondition condition = new OperationGroupListPagerCondition();
		condition.setOperationGroupCd("OPERATION_GROUP_CD001");

		/* getList 実行 */
		List<OperationGroupList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OperationGroupListDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("OPERATION_GROUP");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
