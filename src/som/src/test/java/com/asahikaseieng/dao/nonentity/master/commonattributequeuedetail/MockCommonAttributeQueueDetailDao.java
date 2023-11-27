/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.commonattributequeuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCommonAttributeQueueDetailDaoクラス
 * @author t0011036
 */
public class MockCommonAttributeQueueDetailDao implements
		CommonAttributeQueueDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockCommonAttributeQueueDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CommonAttributeQueueDetail getEntity(final String itemCd,
			final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		CommonAttributeQueueDetail bean = new CommonAttributeQueueDetail();

		/* CommonAttributeQueueDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * CommonAttributeQueueDetailを生成する
	 * @param bean bean
	 * @return CommonAttributeQueueDetail
	 */
	private void createBean(final CommonAttributeQueueDetail bean) {
		bean.setItemCd("ITEM001");
		bean.setVersion(new BigDecimal("1"));
	}
}
