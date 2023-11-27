/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPaymentListForReportDaoクラス
 * @author t0011036
 */
public class MockPaymentListForReportDao implements PaymentListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockPaymentListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PaymentListForReport> getListForReport(
			final PaymentListConditionForReport condition) {
		List<PaymentListForReport> list = new ArrayList<PaymentListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSupplierCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSupplierCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PaymentListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PaymentListForReportを生成する
	 * @param i インデックス
	 * @return PaymentListForReport
	 */
	private PaymentListForReport createBean(final int i) {
		PaymentListForReport bean = new PaymentListForReport();
		bean.setOrganizationCd("ORGANIZATION_CD" + i);
		bean.setOrganizationName("ORGANIZATION_NAME" + i);
		bean.setSupplierCd("VENDER_CD" + i);
		bean.setVenderName1("VENDER_NAME" + i);
		return bean;
	}
}
