/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatedelete;

import java.math.BigDecimal;

/**
 * EstimateDeleteDaoクラス
 * @author t0011036
 */
public interface EstimateDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.estimatedelete.EstimateDelete.class;

	/** ARGSアノテーション deleteList */
	String deleteList_ARGS = "estimateNo, consecutiveNo";

	/**
	 * deleteListメソッド
	 * 
	 * @param estimateNo estimateNo
	 * @param consecutiveNo consecutiveNo
	 * @return int
	 */
	int deleteList(final String estimateNo, final BigDecimal consecutiveNo);
}
