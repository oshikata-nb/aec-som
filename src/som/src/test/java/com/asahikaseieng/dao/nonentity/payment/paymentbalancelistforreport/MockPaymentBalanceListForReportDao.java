/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPaymentBalanceListForReportDaoクラス
 * @author t0011036
 */
public class MockPaymentBalanceListForReportDao implements
		PaymentBalanceListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockPaymentBalanceListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PaymentBalanceListForReport> getListForReport(
			final PaymentBalanceListConditionForReport condition) {
		List<PaymentBalanceListForReport> list = new ArrayList<PaymentBalanceListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PaymentBalanceListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PaymentBalanceListForReportを生成する
	 * @param i インデックス
	 * @return PaymentBalanceListForReport
	 */
	private PaymentBalanceListForReport createBean(final int i) {
		PaymentBalanceListForReport bean = new PaymentBalanceListForReport();
		bean.setOrganizationCd("CD" + i);
		bean.setOrganizationName("NAME" + i);
		return bean;
	}
}
