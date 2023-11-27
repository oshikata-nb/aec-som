/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverydetail;

/**
 * DeliveryDetailDaoクラス
 * @author t0011036
 */
public interface DeliveryDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "deliveryCd";

	/**
	 * DeliveryDetailメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @return DeliveryDetail
	 */
	DeliveryDetail getEntity(final String deliveryCd);
}
