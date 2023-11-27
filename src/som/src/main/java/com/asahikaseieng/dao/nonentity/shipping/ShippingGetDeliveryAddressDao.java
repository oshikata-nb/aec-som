/*
 * Created on 2011/05/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

/**
 * ShippingGetDeliveryAddressDaoクラス
 * @author t1344224
 */
public interface ShippingGetDeliveryAddressDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingGetDeliveryAddress.class;

	/** ARGSアノテーション getDeliveryAddress */
	String getDeliveryAddress_ARGS = "deliveryCd";

	/**
	 * ShippingGetDeliveryAddressメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @return ShippingGetDeliveryAddress
	 */
	ShippingGetDeliveryAddress getDeliveryAddress(final String deliveryCd);
}
