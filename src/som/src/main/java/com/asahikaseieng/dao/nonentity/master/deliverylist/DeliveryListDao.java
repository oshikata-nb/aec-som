/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverylist;

import java.util.List;

/**
 * DeliveryListDaoクラス
 * @author t0011036
 */
public interface DeliveryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.deliverylist.DeliveryList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<DeliveryList>
	 */
	List<DeliveryList> getList(final DeliveryListPagerCondition condition);
}
