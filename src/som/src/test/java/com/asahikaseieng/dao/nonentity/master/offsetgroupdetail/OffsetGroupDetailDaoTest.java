/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgroupdetail;

import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * OffsetGroupDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class OffsetGroupDetailDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private OffsetGroupDetailDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public OffsetGroupDetailDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * getEntityのテスト
	 */
	public void testGetEntityTx() {
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("OffsetGroupDetailDao_init.xls");

		String offsetGroupCd = "OFFSET_GROUP_CD01";

		/* getList 実行 */
		List<OffsetGroupDetail> list = dao.getEntity(offsetGroupCd);

		/* 検証用データ読み込み */
		DataSet expected = readXls("OffsetGroupDetailDao.expected.xls",
			"getEntity");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("OFFSET_GROUP");

		list = dao.getEntity(offsetGroupCd);
		assertEquals(0, list.size());
	}
}
