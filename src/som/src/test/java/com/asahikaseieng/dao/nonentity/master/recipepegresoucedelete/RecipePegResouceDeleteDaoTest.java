/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.recipepegresoucedelete;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * RecipePegResouceDeleteDaoクラスのテストケース
 * @author t0011036
 */
public final class RecipePegResouceDeleteDaoTest extends AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public RecipePegResouceDeleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * deleteListのテスト
	 */
	public void testDeleteListTx() {
		RecipePegResouceDelete lhs = new RecipePegResouceDelete();
		RecipePegResouceDelete rhs = new RecipePegResouceDelete();

		/* 検索条件設定 */
		setValue(lhs);
		setValue(rhs);

		assertTrue(lhs.equals(rhs));
		assertEquals(lhs.hashCode(), rhs.hashCode());
	}

	/**
	 * 検索条件設定
	 * @param bean
	 */
	private void setValue(final RecipePegResouceDelete bean) {
		bean.setResouceCd("CD001");
		bean.setSeq(new BigDecimal("1"));
	}
}
