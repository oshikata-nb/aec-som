/*
 * Created on 2009/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender.payment;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPaymentVenderForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockPaymentVenderForAutoCompleteDao implements
		PaymentVenderForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockPaymentVenderForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PaymentVenderForAutoComplete> getSearchList(
			final String organizationCd, final String venderCd,
			final String rowlimit) {
		List<PaymentVenderForAutoComplete> list = new ArrayList<PaymentVenderForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PaymentVenderForForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PaymentVenderForForAutoCompleteを生成する
	 * @param i インデックス
	 * @return PaymentVenderForForAutoComplete
	 */
	private PaymentVenderForAutoComplete createBean(final int i) {
		PaymentVenderForAutoComplete bean = new PaymentVenderForAutoComplete();
		bean.setVenderCd("VENDER" + i);
		bean.setVenderShortedName("NAME" + i);
		return bean;
	}
}
