/*
 * Created on 2009/04/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutrecordlist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * InoutRecordListDaoクラスのテストケース
 * @author t0011036
 */
public final class InoutRecordListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private InoutRecordListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public InoutRecordListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("InoutRecordListDao_init.xls");

		InoutRecordListPagerCondition condition = new InoutRecordListPagerCondition();
		condition.setSrhItemCd("ITEM_CD001");
		condition.setSrhOtherCompanyCd1("OTHER_COMPANY_CD001");
		condition.setSrhLocationCd("LOCATION_CD001");
		condition.setSrhInoutDivision(new BigDecimal("1"));

		/* getList 実行 */
		List<InoutRecordList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("InoutRecordListDao_expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("INOUT_RECORD");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
