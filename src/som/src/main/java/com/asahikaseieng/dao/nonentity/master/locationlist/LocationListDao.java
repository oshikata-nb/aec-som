/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlist;

import java.util.List;

/**
 * LocationListDaoクラス
 * @author t0011036
 */
public interface LocationListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.locationlist.LocationList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<LocationList>
	 */
	List<LocationList> getList(final LocationListPagerCondition condition);
}
