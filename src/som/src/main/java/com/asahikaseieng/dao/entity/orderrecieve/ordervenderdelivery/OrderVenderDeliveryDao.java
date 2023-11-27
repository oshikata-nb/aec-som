/*
 * Created on 2020/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderrecieve.ordervenderdelivery;

import java.util.List;


/**
 * OrderVenderDeliveryDaoクラス
 * @author 
 */
public interface OrderVenderDeliveryDao {

	/** BEANアノテーション */
	Class<OrderVenderDelivery> BEAN = OrderVenderDelivery.class;

	/**
	 * Insert.
	 * @param bean OrderVenderDelivery
	 * @return Insert件数
	 */
	int insert(OrderVenderDelivery bean);
	
	/**
	 * Update.
	 * @param bean OrderVenderDelivery
	 * @return Update件数
	 */
	int update(OrderVenderDelivery bean);
	
	/**
	 * delete.
	 * @param bean OrderVenderDelivery
	 * @return delete件数
	 */
	int delete(OrderVenderDelivery bean);
	
	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "VENDER_GROUP_CD,SOM_DELIVERY_CD";
	
	/**
	 * エンティティ取得.
	 * @param venderGroupCd String
	 * @param somDeliveryCd String
	 * @return OrderVenderDelivery
	 */
	OrderVenderDelivery getEntity(String venderGroupCd, String somDeliveryCd);
	
	/** ARGSアノテーション getList */
	String getList_ARGS = "VENDER_GROUP_CD";
	
	/**
	 * リスト取得
	 * @param venderGroupCd String
	 * @return List<OrderVenderDelivery>
	 */
	List<OrderVenderDelivery> getList(String venderGroupCd);

}
