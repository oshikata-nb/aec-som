/*
 * Created on 2009/05/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverylistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockDeliveryListForReportDaoクラス
 * @author t0011036
 */
public class MockDeliveryListForReportDao implements DeliveryListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockDeliveryListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DeliveryListForReport> getListForReport(
			final DeliveryListConditionForReport condition) {
		List<DeliveryListForReport> list = new ArrayList<DeliveryListForReport>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhDeliveryCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhDeliveryCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* DeliveryListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * DeliveryListForReportを生成する
	 * @param i インデックス
	 * @return DeliveryListForReport
	 */
	private DeliveryListForReport createBean(final int i) {
		DeliveryListForReport bean = new DeliveryListForReport();
		bean.setDeliveryCd("DELIVERY" + i);
		bean.setDeliveryName1("NAME" + i);
		return bean;
	}
}
