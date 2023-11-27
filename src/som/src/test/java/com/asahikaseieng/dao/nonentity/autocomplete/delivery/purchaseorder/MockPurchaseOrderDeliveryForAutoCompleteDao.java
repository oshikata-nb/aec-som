/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchaseorder;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPurchaseOrderDeliveryForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockPurchaseOrderDeliveryForAutoCompleteDao implements
		PurchaseOrderDeliveryForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockPurchaseOrderDeliveryForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PurchaseOrderDeliveryForAutoComplete> getSearchList(
			final String deliveryCd, final String rowlimit) {
		List<PurchaseOrderDeliveryForAutoComplete> list = new ArrayList<PurchaseOrderDeliveryForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PurchaseOrderDeliveryForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PurchaseOrderDeliveryForAutoCompleteを生成する
	 * @param i インデックス
	 * @return PurchaseOrderDeliveryForAutoComplete
	 */
	private PurchaseOrderDeliveryForAutoComplete createBean(final int i) {
		PurchaseOrderDeliveryForAutoComplete bean = new PurchaseOrderDeliveryForAutoComplete();
		bean.setDeliveryCd("DELIVERY" + i);
		bean.setSearchKana("NAME" + i);
		return bean;
	}
}
