/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPaymentPlanListForReportDaoクラス
 * @author t0011036
 */
public class MockPaymentPlanListForReportDao implements
		PaymentPlanListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockPaymentPlanListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PaymentPlanListForReport> getListForReport(
			final PaymentPlanListConditionForReport condition) {
		List<PaymentPlanListForReport> list = new ArrayList<PaymentPlanListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PaymentPlanListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PaymentPlanListForReportを生成する
	 * @param i インデックス
	 * @return PaymentPlanListForReport
	 */
	private PaymentPlanListForReport createBean(final int i) {
		PaymentPlanListForReport bean = new PaymentPlanListForReport();
		bean.setOrganizationCd("ORGANIZATION_CD" + i);
		bean.setOrganizationName("ORGANIZATION_NAME" + i);
		bean.setSupplierCd("VENDER_CD" + i);
		bean.setVenderName1("VENDER_NAME" + i);
		return bean;
	}
}
