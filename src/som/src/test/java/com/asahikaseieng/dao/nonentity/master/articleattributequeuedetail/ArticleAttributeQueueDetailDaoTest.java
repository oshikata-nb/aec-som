/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * ArticleAttributeQueueDetailDaoクラスのテストケース
 * @author t0011036
 */
public final class ArticleAttributeQueueDetailDaoTest extends
		AbstractS2DaoTestCase {

	/**
	 * コンストラクター
	 * @param testname テスト名
	 */
	public ArticleAttributeQueueDetailDaoTest(final String testname) {
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
		ArticleAttributeQueueDetail lhs = new ArticleAttributeQueueDetail();
		ArticleAttributeQueueDetail rhs = new ArticleAttributeQueueDetail();

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
	private void setValue(final ArticleAttributeQueueDetail bean) {
		bean.setItemCd("00000616");
		bean.setVersion(new BigDecimal("1"));
		bean.setArticleStatus(new BigDecimal("3"));
		bean.setSellingPrice(new BigDecimal("0"));
		bean.setSafetyLeadTime(new BigDecimal("12"));
	}
}
