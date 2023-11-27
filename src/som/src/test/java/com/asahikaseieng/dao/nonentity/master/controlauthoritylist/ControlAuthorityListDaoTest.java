/*
 * Created on 2009/06/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.controlauthoritylist;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.extension.dataset.DataSet;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ControlAuthorityListDaoクラスのテストケース
 * @author t0011036
 */
public final class ControlAuthorityListDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private ControlAuthorityListDao dao;

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ControlAuthorityListDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ControlAuthorityListDao_init.xls");

		String tantoCd = "TANTO001";
		String organizationCd = "ORGANIZATION001";
		BigDecimal postId = new BigDecimal("1");
		BigDecimal menuId = new BigDecimal("1");
		BigDecimal tabId = new BigDecimal("1");
		BigDecimal controlId = new BigDecimal("1");

		/* getList 実行 */
		List<ControlAuthorityList> list = dao.getList(tantoCd, organizationCd,
			postId, menuId, tabId, controlId);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ControlAuthorityListDao_expected.xls",
			"getList");

		/* 取得内容 検証 */
		assertEquals(expected, list);

		/* データが取得できない場合 */
		deleteTable("CONTROL_AUTHORITY");

		list = dao.getList(tantoCd, organizationCd, postId, menuId, tabId,
			controlId);
		assertEquals(0, list.size());
	}
}
