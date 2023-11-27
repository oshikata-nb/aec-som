/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingNewTagHeader;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepSlipShippingNewTagHeaderクラス.
 * @author kanri-user
 */
public class RepSlipShippingNewTagHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSlipShippingNewTagHeaderBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";
	
	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション deliveryAddressAll */
	public static final String deliveryAddressAll_COLUMN = "DELIVERY_ADDRESS_ALL";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryName2 */
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";

	/** COLUMNアノテーション deliveryTelNo */
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";

	/** COLUMNアノテーション shipFromAddress */
	public static final String shipFromAddress_COLUMN = "SHIP_FROM_ADDRESS";

	/** COLUMNアノテーション shipFromName */
	public static final String shipFromName_COLUMN = "SHIP_FROM_NAME";

	/** COLUMNアノテーション shipFromTelNo */
	public static final String shipFromTelNo_COLUMN = "SHIP_FROM_TEL_NO";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション shippingSlipNo */
	public static final String shippingSlipNo_COLUMN = "SHIPPING_SLIP_NO";
	
	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション reportrOutputNum */
	public static final String reportrOutputNum_COLUMN = "REPOTR_OUTPUT_NUM";

	//
	// インスタンスフィールド
	//
	private String key;
	
	private String orderNo;

	private String deliveryAddressAll;

	private String deliveryName1;

	private String deliveryName2;

	private String deliveryTelNo;

	private String scheduledShippingDate;

	private String deliveryExpectedDate;

	private String deliveryExpectedTime;

	private String shipFromAddress;

	private String shipFromName;

	private String shipFromTelNo;

	private String deliveryCd;

	private String shippingSlipNo;

	private String carryCd;

	private String repotrOutputNum;



	//
	// インスタンスメソッド
	//
	


	/**
	 * orderNoを取得します。
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * keyを取得します。
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * keyを設定します。
	 * @param key key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * orderNoを設定します。
	 * @param orderNo orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * deliveryAddressAllを取得します。
	 * @return deliveryAddressAll
	 */
	public String getDeliveryAddressAll() {
		return deliveryAddressAll;
	}

	/**
	 * deliveryAddressAllを設定します。
	 * @param deliveryAddressAll deliveryAddressAll
	 */
	public void setDeliveryAddressAll(String deliveryAddressAll) {
		this.deliveryAddressAll = deliveryAddressAll;
	}

	/**
	 * deliveryName1を取得します。
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return deliveryName1;
	}

	/**
	 * deliveryName1を設定します。
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * deliveryName2を取得します。
	 * @return deliveryName2
	 */
	public String getDeliveryName2() {
		return deliveryName2;
	}

	/**
	 * deliveryName2を設定します。
	 * @param deliveryName2 deliveryName2
	 */
	public void setDeliveryName2(String deliveryName2) {
		this.deliveryName2 = deliveryName2;
	}

	/**
	 * deliveryTelNoを取得します。
	 * @return deliveryTelNo
	 */
	public String getDeliveryTelNo() {
		return deliveryTelNo;
	}

	/**
	 * deliveryTelNoを設定します。
	 * @param deliveryTelNo deliveryTelNo
	 */
	public void setDeliveryTelNo(String deliveryTelNo) {
		this.deliveryTelNo = deliveryTelNo;
	}

	/**
	 * scheduledShippingDateを取得します。
	 * @return scheduledShippingDate
	 */
	public String getScheduledShippingDate() {
		return scheduledShippingDate;
	}

	/**
	 * scheduledShippingDateを設定します。
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(String scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}

	/**
	 * deliveryExpectedDateを取得します。
	 * @return deliveryExpectedDate
	 */
	public String getDeliveryExpectedDate() {
		return deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDateを設定します。
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(String deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedTimeを取得します。
	 * @return deliveryExpectedTime
	 */
	public String getDeliveryExpectedTime() {
		return deliveryExpectedTime;
	}

	/**
	 * deliveryExpectedTimeを設定します。
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * shipFromAddressを取得します。
	 * @return shipFromAddress
	 */
	public String getShipFromAddress() {
		return shipFromAddress;
	}

	/**
	 * shipFromAddressを設定します。
	 * @param shipFromAddress shipFromAddress
	 */
	public void setShipFromAddress(String shipFromAddress) {
		this.shipFromAddress = shipFromAddress;
	}

	/**
	 * shipFromNameを取得します。
	 * @return shipFromName
	 */
	public String getShipFromName() {
		return shipFromName;
	}

	/**
	 * shipFromNameを設定します。
	 * @param shipFromName shipFromName
	 */
	public void setShipFromName(String shipFromName) {
		this.shipFromName = shipFromName;
	}

	/**
	 * shipFromTelNoを取得します。
	 * @return shipFromTelNo
	 */
	public String getShipFromTelNo() {
		return shipFromTelNo;
	}

	/**
	 * shipFromTelNoを設定します。
	 * @param shipFromTelNo shipFromTelNo
	 */
	public void setShipFromTelNo(String shipFromTelNo) {
		this.shipFromTelNo = shipFromTelNo;
	}

	/**
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * shippingSlipNoを取得します。
	 * @return shippingSlipNo
	 */
	public String getShippingSlipNo() {
		return shippingSlipNo;
	}

	/**
	 * shippingSlipNoを設定します。
	 * @param shippingSlipNo shippingSlipNo
	 */
	public void setShippingSlipNo(String shippingSlipNo) {
		this.shippingSlipNo = shippingSlipNo;
	}

	/**
	 * carryCdを取得します。
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * carryCdを設定します。
	 * @param carryCd carryCd
	 */
	public void setCarryCd(String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * repotrOutputNumを取得します。
	 * @return repotrOutputNum
	 */
	public String getRepotrOutputNum() {
		return repotrOutputNum;
	}

	/**
	 * repotrOutputNumを設定します。
	 * @param repotrOutputNum repotrOutputNum
	 */
	public void setRepotrOutputNum(String repotrOutputNum) {
		this.repotrOutputNum = repotrOutputNum;
	}

}

