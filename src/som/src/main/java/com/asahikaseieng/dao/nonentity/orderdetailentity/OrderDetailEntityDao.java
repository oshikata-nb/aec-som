/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailentity;

/**
 * OrderDetailEntityDaoクラス
 * @author kanri-user
 */
public interface OrderDetailEntityDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderdetailentity.OrderDetailEntity.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "orderNo";

	/**
	 * OrderDetailEntityメソッド
	 * 
	 * @param orderNo orderNo
	 * @return OrderDetailEntity
	 */
	OrderDetailEntity getEntity(final String orderNo);
}
