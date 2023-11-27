/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailentity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderDetailEntityクラス.
 * @author kanri-user
 */
public class OrderDetailEntityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderDetailEntityBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション salesTantoCd */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/** COLUMNアノテーション address */
	public static final String address_COLUMN = "ADDRESS";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション orderAmount */
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション suggestedDeliverlimit */
	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション leadTime */
	public static final String leadTime_COLUMN = "LEAD_TIME";

	/** COLUMNアノテーション carryFare */
	public static final String carryFare_COLUMN = "CARRY_FARE";

	/** COLUMNアノテーション carryInvoiceFlag */
	public static final String carryInvoiceFlag_COLUMN = "CARRY_INVOICE_FLAG";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";

	/** COLUMNアノテーション customerOrderNo */
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";

	/** COLUMNアノテーション orderPicture */
	public static final String orderPicture_COLUMN = "ORDER_PICTURE";

	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";

	/** COLUMNアノテーション orderSummery */
	public static final String orderSummery_COLUMN = "ORDER_SUMMERY";
	
	/** COLUMNアノテーション printSummery */
	public static final String printSummery_COLUMN = "PRINT_SUMMERY";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String orderNo;

	private java.math.BigDecimal orderDivision;

	private java.sql.Timestamp orderDate;

	private String salesTantoCd;

	private String deliveryCd;

	private String deliveryName1;

	private String deliveryAddress;

	private String address;

	private String telNo;

	private java.math.BigDecimal orderAmount;

	private String organizationCd;

	private String organizationName;

	private String balanceCd;

	private java.sql.Timestamp suggestedDeliverlimit;

	private java.sql.Timestamp scheduledShippingDate;

	private java.math.BigDecimal leadTime;

	private java.math.BigDecimal carryFare;

	private java.math.BigDecimal carryInvoiceFlag;

	private String carryCd;

	private java.sql.Timestamp deliveryExpectedDate;

	private String deliveryExpectedTime;

	private String customerOrderNo;

	private String orderPicture;

	private String deliverySlipSummery;

	private String orderSummery;
	
	private String printSummery;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

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
	 * orderDivision取得.
	 * @return orderDivision
	 */
	public java.math.BigDecimal getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * orderDivision設定.
	 * @param orderDivision orderDivision
	 */
	public void setOrderDivision(final java.math.BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * orderDate取得.
	 * @return orderDate
	 */
	public java.sql.Timestamp getOrderDate() {
		return this.orderDate;
	}

	/**
	 * orderDate設定.
	 * @param orderDate orderDate
	 */
	public void setOrderDate(final java.sql.Timestamp orderDate) {
		this.orderDate = orderDate;
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
	 * adress取得.
	 * @return adress
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * adress設定.
	 * @param adress adress
	 */
	public void setAddress(final String adress) {
		this.address = adress;
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
	 * orderAmount取得.
	 * @return orderAmount
	 */
	public java.math.BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	/**
	 * orderAmount設定.
	 * @param orderAmount orderAmount
	 */
	public void setOrderAmount(final java.math.BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * balanceCd取得.
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * balanceCd設定.
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * suggestedDeliverlimit取得.
	 * @return suggestedDeliverlimit
	 */
	public java.sql.Timestamp getSuggestedDeliverlimit() {
		return this.suggestedDeliverlimit;
	}

	/**
	 * suggestedDeliverlimit設定.
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(
			final java.sql.Timestamp suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
	}

	/**
	 * scheduledShippingDate取得.
	 * @return scheduledShippingDate
	 */
	public java.sql.Timestamp getScheduledShippingDate() {
		return this.scheduledShippingDate;
	}

	/**
	 * scheduledShippingDate設定.
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(
			final java.sql.Timestamp scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}

	/**
	 * leadTime取得.
	 * @return leadTime
	 */
	public java.math.BigDecimal getLeadTime() {
		return this.leadTime;
	}

	/**
	 * leadTime設定.
	 * @param leadTime leadTime
	 */
	public void setLeadTime(final java.math.BigDecimal leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * carryFare取得.
	 * @return carryFare
	 */
	public java.math.BigDecimal getCarryFare() {
		return this.carryFare;
	}

	/**
	 * carryFare設定.
	 * @param carryFare carryFare
	 */
	public void setCarryFare(final java.math.BigDecimal carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * carryInvoiceFlag取得.
	 * @return carryInvoiceFlag
	 */
	public java.math.BigDecimal getCarryInvoiceFlag() {
		return this.carryInvoiceFlag;
	}

	/**
	 * carryInvoiceFlag設定.
	 * @param carryInvoiceFlag carryInvoiceFlag
	 */
	public void setCarryInvoiceFlag(final java.math.BigDecimal carryInvoiceFlag) {
		this.carryInvoiceFlag = carryInvoiceFlag;
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
	 * deliveryExpectedDate取得.
	 * @return deliveryExpectedDate
	 */
	public java.sql.Timestamp getDeliveryExpectedDate() {
		return this.deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDate設定.
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(
			final java.sql.Timestamp deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedTime取得.
	 * @return deliveryExpectedTime
	 */
	public String getDeliveryExpectedTime() {
		return this.deliveryExpectedTime;
	}

	/**
	 * deliveryExpectedTime設定.
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(final String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * customerOrderNo取得.
	 * @return customerOrderNo
	 */
	public String getCustomerOrderNo() {
		return this.customerOrderNo;
	}

	/**
	 * customerOrderNo設定.
	 * @param customerOrderNo customerOrderNo
	 */
	public void setCustomerOrderNo(final String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	/**
	 * deliverySlipSummery取得.
	 * @return deliverySlipSummery
	 */
	public String getDeliverySlipSummery() {
		return this.deliverySlipSummery;
	}

	/**
	 * deliverySlipSummery設定.
	 * @param deliverySlipSummery deliverySlipSummery
	 */
	public void setDeliverySlipSummery(final String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
	}

	/**
	 * orderSummery取得.
	 * @return orderSummery
	 */
	public String getOrderSummery() {
		return this.orderSummery;
	}

	/**
	 * orderSummery設定.
	 * @param orderSummery orderSummery
	 */
	public void setOrderSummery(final String orderSummery) {
		this.orderSummery = orderSummery;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
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

	/**
	 * orderPictureを取得します。
	 * @return orderPicture
	 */
	public String getOrderPicture() {
		return orderPicture;
	}

	/**
	 * orderPictureを設定します。
	 * @param orderPicture orderPicture
	 */
	public void setOrderPicture(final String orderPicture) {
		this.orderPicture = orderPicture;
	}

	/**
	 * salesTantoCdを取得します。
	 * @return salesTantoCd
	 */
	public String getSalesTantoCd() {
		return salesTantoCd;
	}

	/**
	 * salesTantoCdを設定します。
	 * @param salesTantoCd salesTantoCd
	 */
	public void setSalesTantoCd(final String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * printSummeryを取得します。
	 * @return printSummery
	 */
	public String getPrintSummery() {
		return printSummery;
	}

	/**
	 * printSummeryを設定します。
	 * @param printSummery printSummery
	 */
	public void setPrintSummery(String printSummery) {
		this.printSummery = printSummery;
	}
}
