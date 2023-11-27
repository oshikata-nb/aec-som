/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatedetaillist;

import java.util.List;

/**
 * EstimateDetailListDaoクラス
 * @author t0011036
 */
public interface EstimateDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.estimatedetaillist.EstimateDetailList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "estimateNo";

	/**
	 * Listメソッド
	 * 
	 * @param estimateNo estimateNo
	 * @return List<EstimateDetailList>
	 */
	List<EstimateDetailList> getList(final String estimateNo);
}
