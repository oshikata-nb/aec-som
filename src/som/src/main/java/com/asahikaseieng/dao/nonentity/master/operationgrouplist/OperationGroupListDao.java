/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationgrouplist;

import java.util.List;

/**
 * OperationGroupListDaoクラス
 * @author t0011036
 */
public interface OperationGroupListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.operationgrouplist.OperationGroupList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<OperationGroupList>
	 */
	List<OperationGroupList> getList(
			final OperationGroupListPagerCondition condition);
}
