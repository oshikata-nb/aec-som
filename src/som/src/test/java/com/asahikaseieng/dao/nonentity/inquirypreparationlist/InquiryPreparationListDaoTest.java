/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquirypreparationlist;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * InquiryPreparationListDaoクラスのテストケース
 * @author t0011036
 */
public final class InquiryPreparationListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InquiryPreparationListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InquiryPreparationListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InquiryPreparationListDao_init.xls");

		Timestamp countDate = AecDateUtils.getCurrentTimestamp();
		String countDivision = "1";

		/* getList 実行 */
		List<InquiryPreparationList> list = dao.getList(countDate,
			countDivision);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InquiryPreparationListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOT_INVENTORY");

		list = dao.getList(countDate, countDivision);
		assertEquals(0, list.size());
	}
}
