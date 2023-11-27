/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.checkholiday;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCheckHolidayDaoクラス
 * @author t0011036
 */
public class MockCheckHolidayDao implements CheckHolidayDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockCheckHolidayDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CheckHolidayDetail getCalHoliday(final String calCd,
			final Timestamp caldate) {
		if (Constants.TEST_PARAMETER_NODATA.equals(calCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(calCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		CheckHolidayDetail bean = new CheckHolidayDetail();

		/* CheckHolidayDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * CheckHolidayDetailを生成する
	 * @param bean CheckHolidayDetail
	 * @return CheckHolidayDetail
	 */
	private void createBean(final CheckHolidayDetail bean) {
		bean.setCalendarCd("CAL01");
		bean.setCalHoliday(new BigDecimal("1"));
	}

	/**
	 * {@inheritDoc}
	 */
	public CheckHolidayDetail getVenderCal(final String venderDivision,
			final String venderCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(venderDivision)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		CheckHolidayDetail bean = new CheckHolidayDetail();

		/* CheckHolidayDetailを生成する */
		createBean(bean);

		return bean;
	}
}
