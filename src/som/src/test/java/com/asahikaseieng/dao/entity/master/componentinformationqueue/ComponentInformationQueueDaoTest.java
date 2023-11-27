/*
 * Created on Thu Aug 16 08:46:18 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.componentinformationqueue;

import java.math.BigDecimal;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.extension.dataset.DataSet;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * ComponentInformationQueueDaoクラスのテストケース
 * @author t0011036
 */
public final class ComponentInformationQueueDaoTest extends
		AbstractS2DaoTestCase {

	/** Daoオブジェクト */
	private com.asahikaseieng.dao.entity.master.componentinformationqueue.ComponentInformationQueueDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ComponentInformationQueueDaoTest(final String testname) {
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
		readXlsAllReplaceDb("ComponentInformationQueueDao_init.xls");

		/* getEntity 実行 */
		ComponentInformationQueue bean = dao.getEntity("1",
			new BigDecimal("1"), new BigDecimal("1"));

		/* 検証用データ読み込み */
		DataSet expected = readXls("ComponentInformationQueueDao_expected.xls",
			"getEntity");

		/* 取得内容 検証 */
		assertEquals(expected, bean);

		/* データが取得できない場合 */
		bean = null;
		bean = dao.getEntity("9999999999", new BigDecimal("0"), new BigDecimal(
				"0"));
		assertNull(bean);
	}

	/**
	 * updateのテスト.
	 */
	public void testUpdateTx() {

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("ComponentInformationQueueDao_init.xls");

		/* Bean情報作成 */
		ComponentInformationQueue bean = dao.getEntity("1",
			new BigDecimal("1"), new BigDecimal("1"));

		/* 値を変える */
		bean.setComponentCd("10"); // 成分コード
		bean.setCalcValue(new BigDecimal("20")); // 計算用数値
		bean.setIndicateValue("30"); // 表示用数値
		bean.setUpdatorCd("b"); // 更新者コード

		try {
			/* update 実行 */
			dao.update(bean);

			/* 検証用データ読み込み */
			DataSet expected = readXls(
				"ComponentInformationQueueDao_expected.xls", "update");

			/* DBのデータを取得 */
			DataSet result = readDbByTb("COMPONENT_INFORMATION_QUEUE");

			/* 取得内容 検証 */
			assertEquals(expected, result);

		} catch (NotSingleRowUpdatedRuntimeException e) {
			fail("Should not raise an "
					+ NotSingleRowUpdatedRuntimeException.class);
		}
	}

	/**
	 * insertのテスト.
	 */
	public void testInsertTx() {

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("ComponentInformationQueueDao_init.xls");

		/* Bean情報作成 */
		ComponentInformationQueue bean = new ComponentInformationQueue();

		/* 値を変える */
		bean.setItemCd("2"); // 品目コード
		bean.setVersion(new BigDecimal("2")); // バージョン
		bean.setIndicateOrder(new BigDecimal("1")); // 表示順
		bean.setComponentCd("100"); // 成分コード
		bean.setCalcValue(new BigDecimal("200")); // 計算用数値
		bean.setIndicateValue("300"); // 表示用数値
		bean.setInputDate(AecDateUtils.getTimestampYmdFormat("2007/08/17")); // 登録日時
		bean.setInputorCd("c"); // 登録者コード
		bean.setUpdatorCd("c"); // 更新者コード

		try {
			/* insert 実行 */
			dao.insert(bean);

			/* 検証用データ読み込み */
			DataSet expected = readXls(
				"ComponentInformationQueueDao_expected.xls", "insert");

			/* DBのデータを取得 */
			DataSet result = readDbByTb("COMPONENT_INFORMATION_QUEUE");

			/* 取得内容 検証 */
			assertEquals(expected, result);

		} catch (SQLRuntimeException e) {
			fail("Should not raise an " + DuplicateRuntimeException.class);
		}
	}

	/**
	 * deleteのテスト.
	 */
	public void testDeleteTx() {

		/* 初期値データ書き込み */
		readXlsAllReplaceDb("ComponentInformationQueueDao_init.xls");

		/* Bean情報作成 */
		ComponentInformationQueue bean = dao.getEntity("1",
			new BigDecimal("1"), new BigDecimal("1"));

		/* delete 実行 */
		dao.delete(bean);

		/* 検証用データ読み込み */
		DataSet expected = readXls("ComponentInformationQueueDao_expected.xls",
			"delete");

		/* DBのデータを取得 */
		DataSet result = readDbByTb("COMPONENT_INFORMATION_QUEUE");

		/* 取得内容 検証 */
		assertEquals(expected, result);
	}
}
