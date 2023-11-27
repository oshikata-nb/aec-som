/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockArticleAttributeQueueDetailDaoクラス
 * @author t0011036
 */
public class MockArticleAttributeQueueDetailDao implements
		ArticleAttributeQueueDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockArticleAttributeQueueDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ArticleAttributeQueueDetail getEntity(final String itemCd,
			final java.math.BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ArticleAttributeQueueDetail bean = new ArticleAttributeQueueDetail();

		/* ArticleAttributeQueueDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ArticleAttributeQueueDetailを生成する
	 * @param i インデックス
	 * @return ArticleAttributeQueueDetail
	 */
	private void createBean(final ArticleAttributeQueueDetail bean) {
		bean.setItemCd("00000616");
		bean.setVersion(new BigDecimal("1"));
		bean.setArticleStatus(new BigDecimal("3"));
		bean.setSellingPrice(new BigDecimal("0"));
		bean.setSafetyLeadTime(new BigDecimal("12"));
	}
}
