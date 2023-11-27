/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailentity;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOrderDetailEntityDaoクラス
 * @author kanri-user
 */
public class MockOrderDetailEntityDao implements OrderDetailEntityDao {

	/**
	 * コンストラクタ.
	 */
	public MockOrderDetailEntityDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public OrderDetailEntity getEntity(final String orderNo) {
		if (Constants.TEST_PARAMETER_NODATA.equals(orderNo)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(orderNo)) {
			throw new LargeAmountDataRuntimeException();
		}

		OrderDetailEntity bean = new OrderDetailEntity();

		/* UnitpriceDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * OrderDetailEntityを生成する
	 * @param bean OrderDetailEntity
	 * @return OrderDetailEntity
	 */
	private void createBean(final OrderDetailEntity bean) {
		bean.setOrderNo("ORDER_NO");
	}
}
