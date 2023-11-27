/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * EstimateListDaoクラスのテストケース
 * @author t0011036
 */
public final class EstimateListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private EstimateListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public EstimateListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("EstimateListDao_init.xls");

		EstimateListPagerCondition condition = new EstimateListPagerCondition();
		condition.setSrhEstimateNo("ESTIMATE_NO001");
		condition.setSrhVenderCd("VENDER_CD001");
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhEstimateStatus(new BigDecimal("1"));

		/* getList 実行 */
		List<EstimateList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("EstimateListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("ESTIMATE");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
