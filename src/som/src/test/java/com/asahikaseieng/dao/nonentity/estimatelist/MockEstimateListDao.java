/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockEstimateListDaoクラス
 * @author t0011036
 */
public class MockEstimateListDao implements EstimateListDao {

	/**
	 * コンストラクタ.
	 */
	public MockEstimateListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EstimateList> getList(final EstimateListPagerCondition condition) {
		List<EstimateList> list = new ArrayList<EstimateList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhEstimateNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhEstimateNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* EstimateListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * EstimateListを生成する
	 * @param i インデックス
	 * @return EstimateList
	 */
	private EstimateList createBean(final int i) {
		EstimateList bean = new EstimateList();
		bean.setEstimateNo("ESTIMATE_NO" + i);
		bean.setVenderCd("VENDER_CD" + i);
		bean.setVenderName1("VENDER_NAME" + i);
		return bean;
	}
}
