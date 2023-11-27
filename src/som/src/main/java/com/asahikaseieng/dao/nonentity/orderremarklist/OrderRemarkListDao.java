/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderremarklist;

import java.util.List;

/**
 * OrderRemarkListDaoインターフェース.
 * @author tosco
 */
public interface OrderRemarkListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList.class;

	// 取引先区分、取引先コードで検索-----------------------------------------------------
	/** ARGSアノテーション getRemarkListNoItem */
	String getRemarkListNoItem_ARGS = "venderCd,deliveryCd";

	/**
	 * getRemarkListNoItemメソッド
	 * 
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @return OrderRemarkList
	 */
	List<OrderRemarkList> getRemarkListNoItem(final String venderCd,
			final String deliveryCd);

	// 取引先区分、取引先コード、品目で検索-----------------------------------------------------
	/** ARGSアノテーション getRemarkListWithItem */
	String getRemarkListWithItem_ARGS = "venderCd,deliveryCd,itemCd";

	/**
	 * getRemarkListWithItemメソッド
	 * 
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @return OrderRemarkList
	 */
	List<OrderRemarkList> getRemarkListWithItem(final String venderCd,
			final String deliveryCd, final String itemCd);

	// 取引先区分、取引先コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getRemarkList */
	String getRemarkList_ARGS = "venderCd, deliveryCd, itemCd";

	/**
	 * getRemarkListメソッド
	 * 
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @return OrderRemarkList
	 */
	List<OrderRemarkList> getRemarkList(final String venderCd,
			final String deliveryCd, final String itemCd);

	// 取引先区分、取引先コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getRemarkList */
	String getRemarkDeliveryOnly_ARGS = "deliveryCd";

	/**
	 * getRemarkListメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @return OrderRemarkList
	 */
	OrderRemarkList getRemarkDeliveryOnly(final String deliveryCd);

}
