/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apbalanceforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockApBalanceListForReportDaoクラス
 * @author t0011036
 */
public class MockApBalanceListForReportDao implements ApBalanceListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockApBalanceListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ApBalanceListForReport> getListForReport(
			final ApBalanceListConditionForReport condition) {
		List<ApBalanceListForReport> list = new ArrayList<ApBalanceListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
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

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ApBalanceListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ApBalanceListForReport> getListTempForReport(
			final ApBalanceListConditionForReport condition) {
		List<ApBalanceListForReport> list = new ArrayList<ApBalanceListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
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

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ApBalanceListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ApBalanceListForReportを生成する
	 * @param i インデックス
	 * @return ApBalanceListForReport
	 */
	private ApBalanceListForReport createBean(final int i) {
		ApBalanceListForReport bean = new ApBalanceListForReport();
		bean.setOrganizationCd("ORGANIZATION" + i);
		bean.setInputorCd("INPUTOR" + i);
		bean.setSupplierCd("VENDER" + i);
		return bean;
	}
}
