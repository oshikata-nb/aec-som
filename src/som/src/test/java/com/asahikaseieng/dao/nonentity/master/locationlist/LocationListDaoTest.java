/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlist;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * LocationListDaoクラスのテストケース
 * @author t0011036
 */
public final class LocationListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private LocationListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public LocationListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("LocationListDao_init.xls");

		LocationListPagerCondition condition = new LocationListPagerCondition();
		condition.setSrhLocationCd("LOCATION_CD001");

		/* getList 実行 */
		List<LocationList> list = dao.getList(condition);

		/* 検証用データ読み込み */
		DataSet expected = readXls("LocationListDao.expected.xls", "getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("LOCATION");

		list = dao.getList(condition);
		assertEquals(0, list.size());
	}
}
