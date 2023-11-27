/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderlist;

import java.util.List;

/**
 * OrderListDaoクラス
 * @author kanri-user
 */
public interface OrderListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderlist.OrderList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List
	 */
	List<OrderList> getList(OrderListPagerCondition condition);
}
