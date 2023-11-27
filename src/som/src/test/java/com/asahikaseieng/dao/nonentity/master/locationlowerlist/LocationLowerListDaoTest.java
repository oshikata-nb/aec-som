/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlowerlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LocationLowerListDaoクラスのテストケース
 * @author t0011036
 */
public final class LocationLowerListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LocationLowerListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LocationLowerListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("LocationLowerListDao_init.xls");

		String upperLocationCd = "UPPER_LOCATION_CD";

		/* getList 実行 */
		List<LocationLowerList> list = dao.getList(upperLocationCd);

		/* 検証用データ読み込み */
		DataSet expected = readXls("LocationLowerListDao.expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOCATION");

		list = dao.getList(upperLocationCd);
		assertEquals(0, list.size());
	}
}
