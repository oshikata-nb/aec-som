/*
 * Created on Thu Aug 16 08:45:53 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.component;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

import org.seasar.extension.dataset.DataSet;

/**
 * ComponentDaoクラスのテストケース
 * @author t0011036
 */
public final class ComponentDaoTest extends AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.master.component.ComponentDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ComponentDaoTest(final String testname) {
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
		/* 初期値データ書き込み */
		readXlsAllReplaceDb("ComponentDao_init.xls");

		/* getEntity 実行 */
		Component bean = dao.getEntity("1");

		/* 検証用データ読み込み */
		DataSet expected = readXls("ComponentDao_expected.xls", "getEntity");

		/* 取得内容 検証 */
		assertEquals(expected, bean);

		/* データが取得できない場合 */
		bean = null;
		bean = dao.getEntity("9999999999");
		assertNull(bean);
	}
}
