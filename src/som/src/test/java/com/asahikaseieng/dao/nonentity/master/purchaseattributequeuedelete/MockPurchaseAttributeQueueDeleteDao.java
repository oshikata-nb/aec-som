/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedelete;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPurchaseAttributeQueueDeleteDaoクラス
 * @author t0011036
 */
public class MockPurchaseAttributeQueueDeleteDao implements
		PurchaseAttributeQueueDeleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockPurchaseAttributeQueueDeleteDao() {
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

		PurchaseAttributeQueueDelete bean = new PurchaseAttributeQueueDelete();

		/* PurchaseAttributeQueueDeleteを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * PurchaseAttributeQueueDeleteを生成する
	 * @param bean bean
	 * @return PurchaseAttributeQueueDelete
	 */
	private void createBean(final PurchaseAttributeQueueDelete bean) {
		bean.setItemCd("CD001");
		bean.setVersion(new BigDecimal("1"));
	}
}
