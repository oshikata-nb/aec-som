/*
 * Created on 2009/05/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockUnitpriceListForReportDaoクラス
 * @author t0011036
 */
public class MockUnitpriceListForReportDao implements UnitpriceListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockUnitpriceListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<UnitpriceListForReport> getListForReport(
			final UnitpriceListConditionForReport condition) {
		List<UnitpriceListForReport> list = new ArrayList<UnitpriceListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhVenderDivision())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
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
				.getSrhVenderDivision())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
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
			/* UnitpriceListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * UnitpriceListForReportを生成する
	 * @param i インデックス
	 * @return UnitpriceListForReport
	 */
	private UnitpriceListForReport createBean(final int i) {
		UnitpriceListForReport bean = new UnitpriceListForReport();
		bean.setVenderDivision("SI");
		bean.setVenderCd("VENDER" + i);
		bean.setItemCd("ITEM" + i);
		bean.setConsecutiveNo(new BigDecimal(i));
		bean.setItemName("NAME" + i);
		bean.setVenderName1("NAME" + i);
		bean.setVersion(new BigDecimal(i));
		return bean;
	}
}
