/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.banklistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBankListForReportDaoクラス
 * @author t0011036
 */
public class MockBankListForReportDao implements BankListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockBankListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BankListForReport> getListForReport(
			final BankListConditionForReport condition) {
		List<BankListForReport> list = new ArrayList<BankListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhBankCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhBankCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhBranchCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhBranchCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhBankMasterCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhBankMasterCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BankListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BankListForReportを生成する
	 * @param i インデックス
	 * @return BankListForReport
	 */
	private BankListForReport createBean(final int i) {
		BankListForReport bean = new BankListForReport();
		bean.setBankCd("BANK" + i);
		bean.setBankName("NAME" + i);
		return bean;
	}
}
