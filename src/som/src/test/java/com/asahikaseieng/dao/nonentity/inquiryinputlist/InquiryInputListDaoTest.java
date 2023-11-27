/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * InquiryInputListDaoクラスのテストケース
 * @author t0011036
 */
public final class InquiryInputListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InquiryInputListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InquiryInputListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InquiryInputListDao_init.xls");

		InquiryInputListPagerCondition condition = new InquiryInputListPagerCondition();
		condition.setSrhCountDate(AecDateUtils.getCurrentTimestamp());
		condition.setSrhLocationCd("LOCATION_CD001");
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setSrhAliasLotNo("ALIAS_LOT_NO001");

		/* getList 実行 */
		List<InquiryInputList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InquiryInputListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("INVENTORY_COUNT");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
