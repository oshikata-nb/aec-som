/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.buyingapproval;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBuyingApprovalDeliveryForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockBuyingApprovalDeliveryForAutoCompleteDao implements
		BuyingApprovalDeliveryForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockBuyingApprovalDeliveryForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BuyingApprovalDeliveryForAutoComplete> getSearchList(
			final String deliveryCd, final String rowlimit) {
		List<BuyingApprovalDeliveryForAutoComplete> list = new ArrayList<BuyingApprovalDeliveryForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BuyingApprovalDeliveryForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BuyingApprovalDeliveryForAutoCompleteを生成する
	 * @param i インデックス
	 * @return BuyingApprovalDeliveryForAutoComplete
	 */
	private BuyingApprovalDeliveryForAutoComplete createBean(final int i) {
		BuyingApprovalDeliveryForAutoComplete bean = new BuyingApprovalDeliveryForAutoComplete();
		bean.setDeliveryCd("DELIVERY" + i);
		bean.setSearchKana("NAME" + i);
		return bean;
	}
}
