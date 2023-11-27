/*
 * Created on Mon Feb 09 15:26:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailaspproduction;

import java.math.BigDecimal;

/**
 * OrderDetailAspProductionDaoインターフェース.
 * @author kanri-user
 */
public interface OrderDetailAspProductionDao {

	/** BEANアノテーション. */
	Class BEAN = OrderDetailAspProduction.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "orderNo,orderRowNo";

	/**
	 * エンティティ取得.
	 * @param orderNo orderNo
	 * @param orderRowNo orderRowNo
	 * @return OrderDetailAspProduction
	 */
	OrderDetailAspProduction getEntity(String orderNo, String orderRowNo);

	/** ARGSアノテーション deleteByOrderNo(). */
	String deleteByOrderNo_ARGS = "orderNo";

	/**
	 * 削除.
	 * @param orderNo orderNo
	 * @return OrderDetailAspProduction
	 */
	int deleteByOrderNo(String orderNo);
	
	/** ARGSアノテーション deleteByOrderNoRowNo(). */
	String deleteByOrderNoRowNo_ARGS = "orderNo, orderRowNo";
	
	/**
	 * 削除.
	 * @param orderNo orderNo
	 * @return OrderDetailAspProduction
	 */
	int deleteByOrderNoRowNo(String orderNo, String orderRowNo);
}
