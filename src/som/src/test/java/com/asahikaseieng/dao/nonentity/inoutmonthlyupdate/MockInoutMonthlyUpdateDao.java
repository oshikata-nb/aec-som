/*
 * Created on 2009/07/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmonthlyupdate;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInoutMonthlyUpdateDaoクラス
 * @author t0011036
 */
public class MockInoutMonthlyUpdateDao implements InoutMonthlyUpdateDao {

	/**
	 * コンストラクタ.
	 */
	public MockInoutMonthlyUpdateDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InoutMonthlyUpdate getInoutCount(final String inputDateFrom,
			final String inputDateTo) {
		if (Constants.TEST_PARAMETER_NODATA.equals(inputDateFrom)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(inputDateTo)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDateFrom)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDateTo)) {
			throw new LargeAmountDataRuntimeException();
		}

		InoutMonthlyUpdate bean = new InoutMonthlyUpdate();

		/* InoutMonthlyUpdateを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InoutMonthlyUpdateを生成する
	 * @param bean InoutMonthlyUpdate
	 * @return InoutMonthlyUpdate
	 */
	private void createBean(final InoutMonthlyUpdate bean) {
		bean.setCnt(new BigDecimal("1"));
	}

	/**
	 * {@inheritDoc}
	 */
	public InoutMonthlyUpdate getMonthlyCount(final String inputDate) {
		if (Constants.TEST_PARAMETER_NODATA.equals(inputDate)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDate)) {
			throw new LargeAmountDataRuntimeException();
		}

		InoutMonthlyUpdate bean = new InoutMonthlyUpdate();

		/* InoutMonthlyUpdateを生成する */
		createBean(bean);

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public int insertMonthly(final String inputDate,
			final String inputDateFrom, final String inputDateTo,
			final String tantoCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(inputDate)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(inputDateFrom)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(inputDateTo)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDate)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDateFrom)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDateTo)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		InoutMonthlyUpdate bean = new InoutMonthlyUpdate();

		/* InoutMonthlyUpdateを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public int updateInout(final String inputDateFrom,
			final String inputDateTo, final String tantoCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(inputDateFrom)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(inputDateTo)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDateFrom)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDateTo)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		InoutMonthlyUpdate bean = new InoutMonthlyUpdate();

		/* InoutMonthlyUpdateを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public int deleteMonthly(final String inputDate) {
		if (Constants.TEST_PARAMETER_NODATA.equals(inputDate)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDate)) {
			throw new LargeAmountDataRuntimeException();
		}

		InoutMonthlyUpdate bean = new InoutMonthlyUpdate();

		/* InoutMonthlyUpdateを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public int updateInoutCancel(final String inputDateFrom,
			final String inputDateTo, final String tantoCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(inputDateFrom)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(inputDateTo)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDateFrom)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDateTo)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		InoutMonthlyUpdate bean = new InoutMonthlyUpdate();

		/* InoutMonthlyUpdateを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public int insertDifferenceMonthly(final String inputDate,
			final String inputPrevDate, final String tantoCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(inputDate)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(inputPrevDate)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputDate)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(inputPrevDate)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		InoutMonthlyUpdate bean = new InoutMonthlyUpdate();

		/* InoutMonthlyUpdateを生成する */
		createBean(bean);

		return 1;
	}
}
