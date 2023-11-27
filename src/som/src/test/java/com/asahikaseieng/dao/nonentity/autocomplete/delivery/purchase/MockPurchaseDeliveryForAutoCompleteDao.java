/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchase;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPurchaseDeliveryForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockPurchaseDeliveryForAutoCompleteDao implements
		PurchaseDeliveryForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockPurchaseDeliveryForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PurchaseDeliveryForAutoComplete> getSearchListDetail(
			final String deliveryCd, final String rowlimit) {
		List<PurchaseDeliveryForAutoComplete> list = new ArrayList<PurchaseDeliveryForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PurchaseDeliveryForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PurchaseDeliveryForAutoCompleteを生成する
	 * @param i インデックス
	 * @return PurchaseDeliveryForAutoComplete
	 */
	private PurchaseDeliveryForAutoComplete createBean(final int i) {
		PurchaseDeliveryForAutoComplete bean = new PurchaseDeliveryForAutoComplete();
		bean.setDeliveryCd("DELIVERY" + i);
		bean.setSearchKana("NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PurchaseDeliveryForAutoComplete> getSearchList(
			final String deliveryCd, final String rowlimit) {
		List<PurchaseDeliveryForAutoComplete> list = new ArrayList<PurchaseDeliveryForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PurchaseDeliveryForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}
}
