/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverydetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockDeliveryDetailDaoクラス
 * @author t0011036
 */
public class MockDeliveryDetailDao implements DeliveryDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockDeliveryDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public DeliveryDetail getEntity(final String deliveryCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		DeliveryDetail bean = new DeliveryDetail();

		/* DeliveryDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * DeliveryDetailを生成する
	 * @param bean bean
	 * @return DeliveryDetail
	 */
	private void createBean(final DeliveryDetail bean) {
		bean.setDeliveryCd("DELIVERY001");
		bean.setDeliveryName1("NAME001");
	}
}
