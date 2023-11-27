/*
 * Created on 2009/07/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshippinglistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepSlipShippingNifudaクラス.
 * @author kanri-user
 */
public class RepSlipShippingNifudaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSlipShippingNifudaBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryName2 */
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション zipcodeNo */
	public static final String zipcodeNo_COLUMN = "ZIPCODE_NO";

	/** COLUMNアノテーション address1 */
	public static final String address1_COLUMN = "ADDRESS1";

	/** COLUMNアノテーション address2 */
	public static final String address2_COLUMN = "ADDRESS2";

	/** COLUMNアノテーション address3 */
	public static final String address3_COLUMN = "ADDRESS3";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	//
	// インスタンスフィールド
	//

	private String key;

	private String deliveryCd;

	private String orderNo;

	private String locationCd;

	private String deliveryName1;

	private String deliveryName2;

	private String deliveryAddress;

	private String telNo;

	private String zipcodeNo;

	private String address1;

	private String address2;

	private String address3;

	private String orderQty;

	private String scheduledShippingDate;

	//
	// インスタンスメソッド
	//

	/**
	 * key取得.
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * key設定.
	 * @param key key
	 */
	public void setKey(final String key) {
		this.key = key;
	}

	/**
	 * deliveryCd取得.
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * deliveryCd設定.
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * orderNo取得.
	 * @return orderNo
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * orderNo設定.
	 * @param orderNo orderNo
	 */
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * locationCd取得.
	 * @return locationCd
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * locationCd設定.
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * deliveryName1取得.
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * deliveryName1設定.
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * deliveryName2取得.
	 * @return deliveryName2
	 */
	public String getDeliveryName2() {
		return this.deliveryName2;
	}

	/**
	 * deliveryName2設定.
	 * @param deliveryName2 deliveryName2
	 */
	public void setDeliveryName2(final String deliveryName2) {
		this.deliveryName2 = deliveryName2;
	}

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
	 * telNo取得.
	 * @return telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * telNo設定.
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * zipcodeNo取得.
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return this.zipcodeNo;
	}

	/**
	 * zipcodeNo設定.
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * address1取得.
	 * @return address1
	 */
	public String getAddress1() {
		return this.address1;
	}

	/**
	 * address1設定.
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2取得.
	 * @return address2
	 */
	public String getAddress2() {
		return this.address2;
	}

	/**
	 * address2設定.
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3取得.
	 * @return address3
	 */
	public String getAddress3() {
		return this.address3;
	}

	/**
	 * address3設定.
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * orderQty取得.
	 * @return orderQty
	 */
	public String getOrderQty() {
		return this.orderQty;
	}

	/**
	 * orderQty設定.
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final String orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * scheduledShippingDate取得.
	 * @return scheduledShippingDate
	 */
	public String getScheduledShippingDate() {
		return this.scheduledShippingDate;
	}

	/**
	 * scheduledShippingDate設定.
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(final String scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
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

