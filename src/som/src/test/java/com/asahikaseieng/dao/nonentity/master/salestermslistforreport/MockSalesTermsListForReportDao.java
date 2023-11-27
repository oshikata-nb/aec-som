/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockSalesTermsListForReportDaoクラス
 * @author t0011036
 */
public class MockSalesTermsListForReportDao implements
		SalesTermsListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockSalesTermsListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SalesTermsListForReport> getListForReport(
			final SalesTermsListConditionForReport condition) {
		List<SalesTermsListForReport> list = new ArrayList<SalesTermsListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhDeliveryCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhDeliveryCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* SalesTermsListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * SalesTermsListForReportを生成する
	 * @param i インデックス
	 * @return SalesTermsListForReport
	 */
	private SalesTermsListForReport createBean(final int i) {
		SalesTermsListForReport bean = new SalesTermsListForReport();
		bean.setBalanceCd("BALANCE_CD" + i);
		bean.setDeliveryCd("DELIVERY_CD" + i);
		bean.setDeliveryName1("DELIVERY_NAME" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		bean.setStyleOfPacking("STYLE_OF_PACKING" + i);
		bean.setVenderCd("VENDER_CD" + i);
		bean.setVenderName1("VENDER_NAME" + i);
		return bean;
	}
}
