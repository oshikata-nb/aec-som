/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.arealist;

import java.util.List;

/**
 * AreaListDaoクラス
 * @author t0011036
 */
public interface AreaListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.arealist.AreaList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<AreaList>
	 */
	List<AreaList> getList(final AreaListPagerCondition condition);
}
