/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelist;

import java.util.List;

/**
 * EstimateListDaoクラス
 * @author t0011036
 */
public interface EstimateListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.estimatelist.EstimateList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<EstimateList>
	 */
	List<EstimateList> getList(final EstimateListPagerCondition condition);
}
