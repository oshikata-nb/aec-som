/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderrecieve.ordervenderitem;

import java.util.List;

/**
 * OrderVenderLinkListDaoクラス
 * @author 
 */
public interface OrderVenderItemListDao {

	/** BEANアノテーション */
	Class<OrderVenderItemList> BEAN = com.asahikaseieng.dao.nonentity.orderrecieve.ordervenderitem.OrderVenderItemList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "venderGroupCd, somItemCd";

	/**
	 * Listメソッド
	 * @param venderGroupCd String
	 * @param somItemCd String
	 * @return List<OrderVenderItemList>
	 */
	List<OrderVenderItemList> getList(final String venderGroupCd, final String somItemCd);
	
	/**
	 * Insert.
	 * @param bean OrderVenderItemList
	 * @return Insert件数
	 */
	int insert(OrderVenderItemList bean);

}
