/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ComponentInformationQueueListDaoクラスのテストケース
 * @author t0011036
 */
public final class ComponentInformationQueueListDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ComponentInformationQueueListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ComponentInformationQueueListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ComponentInformationQueueListDao_init.xls");

		String itemCd = "ITEM001";
		BigDecimal version = new BigDecimal("1");

		/* getList 実行 */
		List<ComponentInformationQueueList> list = dao.getList(itemCd, version);

		/* 検証用データ読み込み */
		DataSet expected = readXls(
			"ComponentInformationQueueListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("COMPONENT_INFORMATION_QUEUE");

		list = dao.getList(itemCd, version);
		assertEquals(0, list.size());
	}
}
