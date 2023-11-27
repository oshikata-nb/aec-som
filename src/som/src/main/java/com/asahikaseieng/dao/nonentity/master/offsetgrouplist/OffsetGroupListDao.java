/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgrouplist;

import java.util.List;

/**
 * OffsetGroupListDaoクラス
 * @author t0011036
 */
public interface OffsetGroupListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<OffsetGroupList>
	 */
	List<OffsetGroupList> getList(final OffsetGroupListPagerCondition condition);
}
