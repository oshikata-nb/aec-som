/*
 * Created on 2009/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentheaderdetail;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPaymentHeaderDetailDaoクラス
 * @author t0011036
 */
public class MockPaymentHeaderDetailDao implements PaymentHeaderDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockPaymentHeaderDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PaymentHeaderDetail> getEntity(final String organizationCd,
			final String supplierCd, final Timestamp paymentDate) {
		List<PaymentHeaderDetail> list = new ArrayList<PaymentHeaderDetail>();

		if (Constants.TEST_PARAMETER_NODATA.equals(organizationCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(organizationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(supplierCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(supplierCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PaymentHeaderDetailを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PaymentHeaderDetailを生成する
	 * @param i インデックス
	 * @return PaymentHeaderDetail
	 */
	private PaymentHeaderDetail createBean(final int i) {
		PaymentHeaderDetail bean = new PaymentHeaderDetail();
		bean.setPaymentNo("PAYMENT_NO" + i);
		return bean;
	}
}
