/*
 * Created on 2009/05/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repEstimateHeader;

import java.util.List;

/**
 * RepEstimateHeaderDaoクラス
 * @author kanri-user
 */
public interface RepEstimateHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repEstimateHeader.RepEstimateHeader.class;

	/** ARGSアノテーション getEstimateHeaderList */
	String getEstimateHeaderList_ARGS = "estimateNo";

	/**
	 * Listメソッド
	 * 
	 * @param estimateno estimateno
	 * @return List
	 */
	List<RepEstimateHeader> getEstimateHeaderList(final String estimateno);
}
