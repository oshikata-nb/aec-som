/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repShippingSlipOrderList;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RepShippingSlipOrderListクラス.
 * @author kanri-user
 */
public class RepShippingSlipOrderListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepShippingSlipOrderListBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション shippingSlipNo */
	public static final String shippingSlipNo_COLUMN = "SHIPPING_SLIP_NO";

	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション upperLocationCd */
	public static final String upperLocationCd_COLUMN = "UPPER_LOCATION_CD";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション carryBC. */
	public static final String carryBC_COLUMN = "CARRY_BC";

	//
	// インスタンスフィールド
	//

	private String shippingSlipNo;

	private String shippingNo;

	private String scheduledShippingDate;

	private String upperLocationCd;

	private String orderNo;

	private String deliveryCd;

	private String venderCd;

	private String deliveryExpectedDate;

	private String carryCd;

	private String carryBC;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//
	/**
	 * shippingSlipNo取得.
	 * @return shippingSlipNo
	 */
	public String getShippingSlipNo() {
		return this.shippingSlipNo;
	}

	/**
	 * shippingSlipNo設定.
	 * @param shippingSlipNo shippingSlipNo
	 */
	public void setShippingSlipNo(final String shippingSlipNo) {
		this.shippingSlipNo = shippingSlipNo;
	}

	/**
	 * shippingNo取得.
	 * @return shippingNo
	 */
	public String getShippingNo() {
		return this.shippingNo;
	}

	/**
	 * shippingNo設定.
	 * @param shippingNo shippingNo
	 */
	public void setShippingNo(final String shippingNo) {
		this.shippingNo = shippingNo;
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
	 * upperLocationCd取得.
	 * @return upperLocationCd
	 */
	public String getUpperLocationCd() {
		return this.upperLocationCd;
	}

	/**
	 * upperLocationCd設定.
	 * @param upperLocationCd upperLocationCd
	 */
	public void setUpperLocationCd(final String upperLocationCd) {
		this.upperLocationCd = upperLocationCd;
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
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * deliveryExpectedDate取得.
	 * @return deliveryExpectedDate
	 */
	public String getDeliveryExpectedDate() {
		return this.deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDate設定.
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(final String deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * carryBC取得.
	 * @return carryBC
	 */
	public String getCarryBC() {
		return this.carryBC;
	}

	/**
	 * carryBC設定.
	 * @param carryBC carryBC
	 */
	public void setCarryBC(final String carryBC) {
		this.carryBC = carryBC;
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
