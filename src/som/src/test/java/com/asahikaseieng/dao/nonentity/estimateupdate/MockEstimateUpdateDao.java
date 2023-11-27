/*
 * Created on 2009/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimateupdate;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.estimate.Estimate;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * MockEstimateUpdateDaoクラス
 * @author t0011036
 */
public class MockEstimateUpdateDao implements EstimateUpdateDao {

	/**
	 * コンストラクタ.
	 */
	public MockEstimateUpdateDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(final Estimate beanEstimate) {
		EstimateUpdate bean = new EstimateUpdate();

		/* EstimateUpdateを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * EstimateUpdateを生成する
	 * @param bean EstimateUpdate
	 * @return EstimateUpdate
	 */
	private void createBean(final EstimateUpdate bean) {
		bean.setEstimateNo("ESTIMATE_NO001");
		bean.setConsecutiveNo(new BigDecimal("1"));
		bean.setEstimateStatus(new BigDecimal("1"));
		bean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
		bean.setUpdatorCd("UPDATOR_CD");
	}
}
