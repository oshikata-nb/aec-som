/*
 * Created on 2009/09/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatestatusupdate;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * MockEstimateStatusUpdateDaoクラス
 * @author t0011036
 */
public class MockEstimateStatusUpdateDao implements EstimateStatusUpdateDao {

	/**
	 * コンストラクタ.
	 */
	public MockEstimateStatusUpdateDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int updateStatus(final BigDecimal estimateStatus,
			final Timestamp updateDate, final String updatorCd,
			final String estimateNo) {
		EstimateStatusUpdate bean = new EstimateStatusUpdate();

		/* EstimateStatusUpdateを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * EstimateStatusUpdateを生成する
	 * @param bean EstimateStatusUpdate
	 * @return EstimateStatusUpdate
	 */
	private void createBean(final EstimateStatusUpdate bean) {
		bean.setEstimateNo("ESTIMATE_NO001");
		bean.setConsecutiveNo(new BigDecimal("1"));
		bean.setEstimateStatus(new BigDecimal("1"));
		bean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
		bean.setUpdatorCd("UPDATOR_CD");
	}
}
