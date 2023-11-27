/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliveryinfo;

/**
 * DeliveryDetailDaoクラス
 * @author t0011036
 */
public interface DeliveryInfoDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "deliveryCd";

	/**
	 * DeliveryDetailメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @return DeliveryInfo
	 */
	DeliveryInfo getEntity(final String deliveryCd);
}
