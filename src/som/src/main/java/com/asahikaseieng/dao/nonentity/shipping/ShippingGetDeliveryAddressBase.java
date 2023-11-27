/*
 * Created on 2011/05/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ShippingGetDeliveryAddressクラス.
 * @author t1344224
 */
public class ShippingGetDeliveryAddressBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingGetDeliveryAddressBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	//
	// インスタンスフィールド
	//

	private String deliveryAddress;

	//
	// インスタンスメソッド
	//

	/**
	 * deliveryAddress取得.
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * deliveryAddress設定.
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}

