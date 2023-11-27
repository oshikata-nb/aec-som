/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.productattributequeuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockProductAttributeQueueDetailDaoクラス
 * @author t0011036
 */
public class MockProductAttributeQueueDetailDao implements
		ProductAttributeQueueDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockProductAttributeQueueDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductAttributeQueueDetail getEntity(final String itemCd,
			final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ProductAttributeQueueDetail bean = new ProductAttributeQueueDetail();

		/* ProductAttributeQueueDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ProductAttributeQueueDetailを生成する
	 * @param bean bean
	 * @return ProductAttributeQueueDetail
	 */
	private void createBean(final ProductAttributeQueueDetail bean) {
		bean.setItemCd("CD001");
		bean.setVersion(new BigDecimal("1"));
	}
}
