/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.orderstatuslist;

import java.util.List;

/**
 * OrderStatusListDaoクラス
 * @author kanri-user
 */
public interface OrderStatusListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "nameCd";

	/**
	 * Listメソッド
	 * 
	 * @param nameCd nameCd
	 * @return List<OrderStatusList>
	 */
	List<OrderStatusList> getList(final String nameCd);
}
