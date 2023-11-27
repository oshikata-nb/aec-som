/*
 * Created on 2009/09/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatestatusupdate;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * EstimateStatusUpdateDaoクラス
 * @author t0011036
 */
public interface EstimateStatusUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.estimatestatusupdate.EstimateStatusUpdate.class;

	/** ARGSアノテーション updateStatus */
	String updateStatus_ARGS = "estimateStatus, updateDate, updatorCd, estimateNo";

	/**
	 * EstimateStatusUpdateメソッド
	 * 
	 * @param estimateStatus estimateStatus
	 * @param updateDate updateDate
	 * @param updatorCd updatorCd
	 * @param estimateNo estimateNo
	 * @return int 更新件数
	 */
	int updateStatus(final BigDecimal estimateStatus,
			final Timestamp updateDate, final String updatorCd,
			final String estimateNo);
}
