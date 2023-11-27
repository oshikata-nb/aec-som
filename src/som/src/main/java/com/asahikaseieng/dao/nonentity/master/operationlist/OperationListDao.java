/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationlist;

import java.util.List;

/**
 * OperationListDaoクラス
 * @author t0011036
 */
public interface OperationListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.operationlist.OperationList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<OperationList>
	 */
	List<OperationList> getList(final OperationListPagerCondition condition);
}
