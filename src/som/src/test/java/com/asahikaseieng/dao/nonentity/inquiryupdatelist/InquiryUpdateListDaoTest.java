/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryupdatelist;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * InquiryUpdateListDaoクラスのテストケース
 * @author t0011036
 */
public final class InquiryUpdateListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InquiryUpdateListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InquiryUpdateListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InquiryUpdateListDao_init.xls");

		Timestamp countDate = AecDateUtils.getCurrentTimestamp();
		String countDivision = "COUNT_DIVISION001";

		/* getList 実行 */
		List<InquiryUpdateList> list = dao.getList(countDate, countDivision,
			null, null, null);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InquiryUpdateListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("INVENTORY_COUNT");

		list = dao.getList(countDate, countDivision, null, null, null);
		assertEquals(0, list.size());
	}
}
