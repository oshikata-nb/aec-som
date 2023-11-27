/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetaillist;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderDetailListDaoクラス
 * @author kanri-user
 */
public interface OrderDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailList.class;

	/** ARGSアノテーション getDetailList */
	String getDetailList_ARGS = "orderNo";

	/**
	 * Listメソッド
	 * 
	 * @param orderNo orderNo
	 * @return List
	 */
	List<OrderDetailList> getDetailList(final String orderNo);


	/** ARGSアノテーション getDetailList */
	String getOrderDetailMaxRow_ARGS = "orderNo";

	/**
	 * Listメソッド
	 * 
	 * @param orderNo orderNo
	 * @return List
	 */
	BigDecimal getOrderDetailMaxRow(final String orderNo);

	/** ARGSアノテーション deleteByOrderNo */
	String deleteByOrderNo_ARGS = "orderNo";

	/**
	 * Listメソッド
	 * 
	 * @param orderNo orderNo
	 * @return List
	 */
	int deleteByOrderNo(final String orderNo);
			
}
