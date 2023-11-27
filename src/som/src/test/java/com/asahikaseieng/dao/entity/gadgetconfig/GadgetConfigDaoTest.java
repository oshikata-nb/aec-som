/*
 * Created on Mon Apr 09 13:53:49 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadgetconfig;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.seasar.extension.dataset.DataSet;

/**
 * GadgetConfigDaoクラスのテストケース
 * @author jbd
 */
public final class GadgetConfigDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfigDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public GadgetConfigDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * エンティティのテスト.
	 */
	public void testEntityTx() {

		GadgetConfig lhs = new GadgetConfig();
		GadgetConfig rhs = new GadgetConfig();

		assertTrue(lhs.equals(rhs));
	}

	/**
	 * getListByTantoCdのテスト.
	 */
	public void testGetListByTantoCdTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("GadgetConfigDao_init.xls");

		/* データが取得できる場合 */
		List<GadgetConfig> list = dao.getListByTantoCd("test000001");

		/* 検証用データ読み込み */
		DataSet expected = readXls("GadgetConfigDao_expected.xls",
			"getListByTantoCd");
		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		list = null;
		/* テーブルの中身を消す */
		deleteTable("GADGET_CONFIG");

		list = dao.getListByTantoCd("test000001");

		assertTrue(list.size() <= 0);
	}

	/**
	 * deleteBytantoCdのテスト
	 */
	public void testDeleteBytantoCdTx() {

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("GadgetConfigDao_init.xls");

		/* 正常に更新できる場合 */
		dao.deleteByTantoCd("test000001");

		/* 検証用データ読み込み */
		DataSet expected = readXls("GadgetConfigDao_expected.xls",
			"deleteBytantoCd");
		/* DBのデータを取得 */
		DataSet result = readDbByTb("GADGET_CONFIG");
		assertEquals(expected, result);
	}

	/**
	 * deleteBytantoCdのテスト
	 */
	public void testInsertTx() {

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("GadgetConfigDao_init.xls");

		/* 正常に更新できる場合 */
		int i = dao.insert(createEntity());
		assertEquals(1, i);

		/* 検証用データ読み込み */
		DataSet expected = readXls("GadgetConfigDao_expected.xls", "insert");
		/* DBのデータを取得 */
		DataSet result = readDbByTb("GADGET_CONFIG");
		assertEquals(expected, result);
	}

	/**
	 * データを１件作成
	 * @return GadgetConfig
	 */
	private GadgetConfig createEntity() {

		GadgetConfig gc = new GadgetConfig();
		gc.setTantoCd("test000003");
		gc.setGadgetId("001");
		gc.setLaneNo(Constants.FIRST_LANE);
		gc.setVerticalOrder(BigDecimal.ZERO);
		return gc;
	}
}
