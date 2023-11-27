/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedelete;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockArticleAttributeQueueDeleteDaoクラス
 * @author t0011036
 */
public class MockArticleAttributeQueueDeleteDao implements
		ArticleAttributeQueueDeleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockArticleAttributeQueueDeleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(final String itemCd, final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ArticleAttributeQueueDelete bean = new ArticleAttributeQueueDelete();

		/* ArticleAttributeQueueDeleteを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * ArticleAttributeQueueDeleteを生成する
	 * @param i インデックス
	 * @return ArticleAttributeQueueDelete
	 */
	private void createBean(final ArticleAttributeQueueDelete bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setVersion(new BigDecimal("1"));
		bean.setSellingPrice(new BigDecimal("0"));
		bean.setSafetyLeadTime(new BigDecimal("0"));
	}
}
