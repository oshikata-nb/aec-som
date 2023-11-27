/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderrecieve.ordervenderdelivery;

import java.util.List;

/**
 * OrderVenderLinkListDaoクラス
 * @author 
 */
public interface OrderVenderDeliveryListDao {

	/** BEANアノテーション */
	Class<OrderVenderDeliveryList> BEAN = com.asahikaseieng.dao.nonentity.orderrecieve.ordervenderdelivery.OrderVenderDeliveryList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "venderGroupCd,somDeliveryCd";

	/**
	 * Listメソッド
	 * @param venderGroupCd String
	 * @param somDeliveryCd String
	 * @return List<OrderVenderDeliveryList>
	 */
	List<OrderVenderDeliveryList> getList(final String venderGroupCd, final String somDeliveryCd);
	
	/**
	 * Insert.
	 * @param bean OrderVenderDeliveryList
	 * @return Insert件数
	 */
	int insert(OrderVenderDeliveryList bean);
	
	/**
	 * Update.
	 * @param bean OrderVenderDeliveryList
	 * @return Update件数
	 */
	int update(OrderVenderDeliveryList bean);

}
