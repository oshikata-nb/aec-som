/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatebalancelist;

import java.util.List;

/**
 * EstimateBalanceListDaoクラス
 * @author t0011036
 */
public interface EstimateBalanceListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.estimatebalancelist.EstimateBalanceList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "balanceCd";

	/**
	 * Listメソッド
	 * 
	 * @param balanceCd balanceCd
	 * @return List<EstimateBalanceList>
	 */
	List<EstimateBalanceList> getList(final String balanceCd);
}
