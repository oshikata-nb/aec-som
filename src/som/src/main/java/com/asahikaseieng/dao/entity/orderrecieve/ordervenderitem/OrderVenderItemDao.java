/*
 * Created on 2020/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderrecieve.ordervenderitem;

import java.util.List;

/**
 * OrderVenderItemDaoクラス
 * @author 
 */
public interface OrderVenderItemDao {

	/** BEANアノテーション */
	Class<OrderVenderItem> BEAN = OrderVenderItem.class;

	/**
	 * Insert.
	 * @param bean OrderVenderItem
	 * @return Insert件数
	 */
	int insert(OrderVenderItem bean);
	
	/**
	 * Update.
	 * @param bean OrderVenderItem
	 * @return Update件数
	 */
	int update(OrderVenderItem bean);
	
	/**
	 * delete.
	 * @param bean OrderVenderItem
	 * @return delete件数
	 */
	int delete(OrderVenderItem bean);
	
	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "VENDER_GROUP_CD,SOM_ITEM_CD";
	
	/**
	 * エンティティ取得.
	 * @param venderGroupCd venderGroupCd
	 * @return OrderVenderItem
	 */
	OrderVenderItem getEntity(String venderGroupCd, String somItemCd);
	
	/** ARGSアノテーション getList */
	String getList_ARGS = "VENDER_GROUP_CD";
	
	/**
	 * リスト取得.
	 * @param venderGroupCd venderGroupCd
	 * @return OrderVenderItem
	 */
	List<OrderVenderItem> getList(String venderGroupCd);

}
