/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimateduplicatelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockEstimateDuplicateListDaoクラス
 * @author t0011036
 */
public class MockEstimateDuplicateListDao implements EstimateDuplicateListDao {

	/**
	 * コンストラクタ.
	 */
	public MockEstimateDuplicateListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EstimateDuplicateList> getDuplicateList(
			final String estimateNo, final String balanceCd,
			final String itemCd, final String strEstimateValidDateFrom,
			final String strEstimateValidDateTo) {
		List<EstimateDuplicateList> list = new ArrayList<EstimateDuplicateList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(estimateNo)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(strEstimateValidDateFrom)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(strEstimateValidDateTo)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(estimateNo)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(strEstimateValidDateFrom)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(strEstimateValidDateTo)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* EstimateDuplicateListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * EstimateDuplicateListを生成する
	 * @param i インデックス
	 * @return EstimateDuplicateList
	 */
	private EstimateDuplicateList createBean(final int i) {
		EstimateDuplicateList bean = new EstimateDuplicateList();
		bean.setEstimateNo("ESTIMATE_NO" + i);
		return bean;
	}
}
