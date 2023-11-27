/*
 * Created on 2009/05/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repEstimateDetail;

import java.util.List;

/**
 * RepEstimateDetailDaoクラス
 * @author kanri-user
 */
public interface RepEstimateDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repEstimateDetail.RepEstimateDetail.class;

	/** ARGSアノテーション getEstimateDetailList */
	String getEstimateDetailList_ARGS = "estimateNo";

	/**
	 * Listメソッド
	 * 
	 * @param estimateno estimateno
	 * @return List
	 */
	List<RepEstimateDetail> getEstimateDetailList(final String estimateno);
}
