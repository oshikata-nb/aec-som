/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.shipping;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockShippingDeliveryForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockShippingDeliveryForAutoCompleteDao implements
		ShippingDeliveryForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockShippingDeliveryForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ShippingDeliveryForAutoComplete> getSearchList(
			final String deliveryCd, final String rowlimit) {
		List<ShippingDeliveryForAutoComplete> list = new ArrayList<ShippingDeliveryForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ShippingDeliveryForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ShippingDeliveryForAutoCompleteを生成する
	 * @param i インデックス
	 * @return ShippingDeliveryForAutoComplete
	 */
	private ShippingDeliveryForAutoComplete createBean(final int i) {
		ShippingDeliveryForAutoComplete bean = new ShippingDeliveryForAutoComplete();
		bean.setDeliveryCd("DELIVERY" + i);
		bean.setSearchKana("NAME" + i);
		return bean;
	}
}
