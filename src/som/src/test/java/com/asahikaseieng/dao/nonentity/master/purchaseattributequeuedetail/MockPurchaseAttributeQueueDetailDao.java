/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;


/**
 * MockPurchaseAttributeQueueDetailDaoクラス
 * @author t0011036
 */
public class MockPurchaseAttributeQueueDetailDao implements PurchaseAttributeQueueDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockPurchaseAttributeQueueDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public PurchaseAttributeQueueDetail getEntity(
		final String itemCd,
		final BigDecimal version
	) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		PurchaseAttributeQueueDetail bean = new PurchaseAttributeQueueDetail();

		/* PurchaseAttributeQueueDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * PurchaseAttributeQueueDetailを生成する
	 * @param bean bean
	 * @return PurchaseAttributeQueueDetail
	 */
	private void createBean(final PurchaseAttributeQueueDetail bean) {
		bean.setItemCd("CD001");
		bean.setVersion(new BigDecimal("1"));
	}
}
