/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockEstimateListForReportDaoクラス
 * @author t0011036
 */
public class MockEstimateListForReportDao implements EstimateListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockEstimateListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EstimateListForReport> getListForReport(
			final EstimateListConditionForReport condition) {
		List<EstimateListForReport> list = new ArrayList<EstimateListForReport>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhEstimateNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhEstimateInputDateFrom())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhEstimateInputDateTo())) {
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

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhEstimateValidDateFrom())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhEstimateValidDateTo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhEstimateNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhEstimateInputDateFrom())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhEstimateInputDateTo())) {
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

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhEstimateValidDateFrom())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhEstimateValidDateTo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* EstimateListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * EstimateListForReportを生成する
	 * @param i インデックス
	 * @return EstimateListForReport
	 */
	private EstimateListForReport createBean(final int i) {
		EstimateListForReport bean = new EstimateListForReport();
		bean.setEstimateNo("ESTIMATE_NO" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		bean.setVenderCd("VENDER_CD" + i);
		bean.setVenderName1("VENDER_NAME" + i);
		return bean;
	}
}
