/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedelete;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ArticleAttributeQueueDeleteDaoクラスのテストケース
 * @author t0011036
 */
public final class ArticleAttributeQueueDeleteDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ArticleAttributeQueueDeleteDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * deleteのテスト
	 */
	public void testDeleteTx() {
		ArticleAttributeQueueDelete lhs = new ArticleAttributeQueueDelete();
		ArticleAttributeQueueDelete rhs = new ArticleAttributeQueueDelete();

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
	private void setValue(final ArticleAttributeQueueDelete bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setVersion(new BigDecimal("1"));
		bean.setSellingPrice(new BigDecimal("0"));
		bean.setSafetyLeadTime(new BigDecimal("0"));
	}
}
