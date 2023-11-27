/*
 * Created on 2021/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportslipforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepOrderSlipHeaderForReportクラス.
 * @author kanri-user
 */
public class RepOrderImportSlipHeaderForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepOrderImportSlipHeaderForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";
	
	/** COLUMNアノテーション pkNo */
	public static final String pkNo_COLUMN = "PK_NO";
	
	/** COLUMNアノテーション pkRow */
	public static final String pkRow_COLUMN = "PK_ROW";

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション divisionName */
	public static final String divisionName_COLUMN = "DIVISION_NAME";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション inputTantoCd */
	public static final String inputTantoCd_COLUMN = "INPUT_TANTO_CD";

	/** COLUMNアノテーション inputTantoName */
	public static final String inputTantoName_COLUMN = "INPUT_TANTO_NAME";

	/** COLUMNアノテーション salesTantoCd */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";

	/** COLUMNアノテーション salesTantoName */
	public static final String salesTantoName_COLUMN = "SALES_TANTO_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/** COLUMNアノテーション deliveryAddressAll */
	public static final String deliveryAddressAll_COLUMN = "DELIVERY_ADDRESS_ALL";

	/** COLUMNアノテーション deliveryTelNo */
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション customerOrderNo */
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";

	/** COLUMNアノテーション orderAmount */
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";

	/** COLUMNアノテーション suggestedDeliverlimit */
	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション leadTime */
	public static final String leadTime_COLUMN = "LEAD_TIME";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryName1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション carryFare */
	public static final String carryFare_COLUMN = "CARRY_FARE";

	/** COLUMNアノテーション carryInvoiceFlag */
	public static final String carryInvoiceFlag_COLUMN = "CARRY_INVOICE_FLAG";

	/** COLUMNアノテーション carryInvoiceString */
	public static final String carryInvoiceString_COLUMN = "CARRY_INVOICE_STRING";

	/** COLUMNアノテーション orderPicture */
	public static final String orderPicture_COLUMN = "ORDER_PICTURE";
	
	/** COLUMNアノテーション printSummery */
	public static final String printSummery_COLUMN = "PRINT_SUMMERY";

	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";

	/** COLUMNアノテーション orderSummery */
	public static final String orderSummery_COLUMN = "ORDER_SUMMERY";

	/** COLUMNアノテーション slipPublishComp */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション slipPublishDate */
	public static final String slipPublishDate_COLUMN = "SLIP_PUBLISH_DATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	/** COLUMNアノテーション orgZipCode */
	public static final String orgZipCode_COLUMN = "ORG_ZIPCODE";

	/** COLUMNアノテーション orgAddress */
	public static final String orgAddress_COLUMN = "ORG_ADDRESS";

	/** COLUMNアノテーション orgTelNo */
	public static final String orgTelNo_COLUMN = "ORG_TEL_NO";

	/** COLUMNアノテーション orgFaxNo */
	public static final String orgFaxNo_COLUMN = "ORG_FAX_NO";

	//
	// インスタンスフィールド
	//

	private String orderNo;
	
	private String pkNo;
	
	private java.math.BigDecimal pkRow;

	private java.math.BigDecimal orderDivision;

	private String divisionName;

	private java.sql.Timestamp orderDate;

	private String organizationCd;

	private String organizationName;

	private String inputTantoCd;

	private String inputTantoName;

	private String salesTantoCd;

	private String salesTantoName;

	private String venderCd;

	private String venderName;

	private String deliveryCd;

	private String deliveryName;

	private String deliveryAddress;

	private String deliveryAddressAll;

	private String deliveryTelNo;

	private String balanceCd;

	private java.math.BigDecimal status;

	private String customerOrderNo;

	private java.math.BigDecimal orderAmount;

	private java.sql.Timestamp suggestedDeliverlimit;

	private java.sql.Timestamp scheduledShippingDate;

	private java.math.BigDecimal leadTime;

	private java.sql.Timestamp deliveryExpectedDate;

	private String deliveryExpectedTime;

	private String carryCd;

	private String carryName1;

	private java.math.BigDecimal carryFare;

	private java.math.BigDecimal carryInvoiceFlag;

	private String carryInvoiceString;

	private String orderPicture;

	private String printSummery;
	
	private String deliverySlipSummery;

	private String orderSummery;

	private java.math.BigDecimal slipPublishComp;

	private java.sql.Timestamp slipPublishDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

	private String orgZipCode;
	private String orgAddress;
	private String orgTelNo;
	private String orgFaxNo;
	
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
	 * pkNoを取得します。
	 * @return pkNo
	 */
	public String getPkNo() {
		return pkNo;
	}

	/**
	 * pkNoを設定します。
	 * @param pkNo pkNo
	 */
	public void setPkNo(String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * pkRowを取得します。
	 * @return pkRow
	 */
	public java.math.BigDecimal getPkRow() {
		return pkRow;
	}

	/**
	 * pkRowを設定します。
	 * @param pkRow pkRow
	 */
	public void setPkRow(java.math.BigDecimal pkRow) {
		this.pkRow = pkRow;
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
	 * divisionName取得.
	 * @return divisionName
	 */
	public String getDivisionName() {
		return this.divisionName;
	}

	/**
	 * divisionName設定.
	 * @param divisionName divisionName
	 */
	public void setDivisionName(final String divisionName) {
		this.divisionName = divisionName;
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
	 * inputTantoCd取得.
	 * @return inputTantoCd
	 */
	public String getInputTantoCd() {
		return this.inputTantoCd;
	}

	/**
	 * inputTantoCd設定.
	 * @param inputTantoCd inputTantoCd
	 */
	public void setInputTantoCd(final String inputTantoCd) {
		this.inputTantoCd = inputTantoCd;
	}

	/**
	 * inputTantoName取得.
	 * @return inputTantoName
	 */
	public String getInputTantoName() {
		return this.inputTantoName;
	}

	/**
	 * inputTantoName設定.
	 * @param inputTantoName inputTantoName
	 */
	public void setInputTantoName(final String inputTantoName) {
		this.inputTantoName = inputTantoName;
	}

	/**
	 * salesTantoCd取得.
	 * @return salesTantoCd
	 */
	public String getSalesTantoCd() {
		return this.salesTantoCd;
	}

	/**
	 * salesTantoCd設定.
	 * @param salesTantoCd salesTantoCd
	 */
	public void setSalesTantoCd(final String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * salesTantoName取得.
	 * @return salesTantoName
	 */
	public String getSalesTantoName() {
		return this.salesTantoName;
	}

	/**
	 * salesTantoName設定.
	 * @param salesTantoName salesTantoName
	 */
	public void setSalesTantoName(final String salesTantoName) {
		this.salesTantoName = salesTantoName;
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
	 * venderName取得.
	 * @return venderName
	 */
	public String getVenderName() {
		return this.venderName;
	}

	/**
	 * venderName設定.
	 * @param venderName venderName
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
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
	 * deliveryName取得.
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return this.deliveryName;
	}

	/**
	 * deliveryName設定.
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(final String deliveryName) {
		this.deliveryName = deliveryName;
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
	 * deliveryAddressAll取得.
	 * @return deliveryAddressAll
	 */
	public String getDeliveryAddressAll() {
		return this.deliveryAddressAll;
	}

	/**
	 * deliveryAddressAll設定.
	 * @param deliveryAddressAll deliveryAddressAll
	 */
	public void setDeliveryAddressAll(final String deliveryAddressAll) {
		this.deliveryAddressAll = deliveryAddressAll;
	}

	/**
	 * deliveryTelNo取得.
	 * @return deliveryTelNo
	 */
	public String getDeliveryTelNo() {
		return this.deliveryTelNo;
	}

	/**
	 * deliveryTelNo設定.
	 * @param deliveryTelNo deliveryTelNo
	 */
	public void setDeliveryTelNo(final String deliveryTelNo) {
		this.deliveryTelNo = deliveryTelNo;
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
	 * status取得.
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
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
	public void setSuggestedDeliverlimit(final java.sql.Timestamp suggestedDeliverlimit) {
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
	public void setScheduledShippingDate(final java.sql.Timestamp scheduledShippingDate) {
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
	public void setDeliveryExpectedDate(final java.sql.Timestamp deliveryExpectedDate) {
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
	 * carryName1取得.
	 * @return carryName1
	 */
	public String getCarryName1() {
		return this.carryName1;
	}

	/**
	 * carryName1設定.
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
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
	 * carryInvoiceString取得.
	 * @return carryInvoiceString
	 */
	public String getCarryInvoiceString() {
		return this.carryInvoiceString;
	}

	/**
	 * carryInvoiceString設定.
	 * @param carryInvoiceString carryInvoiceString
	 */
	public void setCarryInvoiceString(final String carryInvoiceString) {
		this.carryInvoiceString = carryInvoiceString;
	}

	/**
	 * orderPicture取得.
	 * @return orderPicture
	 */
	public String getOrderPicture() {
		return this.orderPicture;
	}

	/**
	 * orderPicture設定.
	 * @param orderPicture orderPicture
	 */
	public void setOrderPicture(final String orderPicture) {
		this.orderPicture = orderPicture;
	}

	/**
	 * printSummery取得.
	 * @return printSummery
	 */
	public String getPrintSummery() {
		return printSummery;
	}

	/**
	 * printSummery設定.
	 * @param printSummery printSummery
	 */
	public void setPrintSummery(String printSummery) {
		this.printSummery = printSummery;
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
	 * slipPublishComp取得.
	 * @return slipPublishComp
	 */
	public java.math.BigDecimal getSlipPublishComp() {
		return this.slipPublishComp;
	}

	/**
	 * slipPublishComp設定.
	 * @param slipPublishComp slipPublishComp
	 */
	public void setSlipPublishComp(final java.math.BigDecimal slipPublishComp) {
		this.slipPublishComp = slipPublishComp;
	}

	/**
	 * slipPublishDate取得.
	 * @return slipPublishDate
	 */
	public java.sql.Timestamp getSlipPublishDate() {
		return this.slipPublishDate;
	}

	/**
	 * slipPublishDate設定.
	 * @param slipPublishDate slipPublishDate
	 */
	public void setSlipPublishDate(final java.sql.Timestamp slipPublishDate) {
		this.slipPublishDate = slipPublishDate;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
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
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
	}

	/**
	 * orgZipCodeを取得します。
	 * @return orgZipCode
	 */
	public String getOrgZipCode() {
		return orgZipCode;
	}

	/**
	 * orgZipCodeを設定します。
	 * @param orgZipCode orgZipCode
	 */
	public void setOrgZipCode(String orgZipCode) {
		this.orgZipCode = orgZipCode;
	}

	/**
	 * orgAddressを取得します。
	 * @return orgAddress
	 */
	public String getOrgAddress() {
		return orgAddress;
	}

	/**
	 * orgAddressを設定します。
	 * @param orgAddress orgAddress
	 */
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	/**
	 * orgTelNoを取得します。
	 * @return orgTelNo
	 */
	public String getOrgTelNo() {
		return orgTelNo;
	}

	/**
	 * orgTelNoを設定します。
	 * @param orgTelNo orgTelNo
	 */
	public void setOrgTelNo(String orgTelNo) {
		this.orgTelNo = orgTelNo;
	}

	/**
	 * orgFaxNoを取得します。
	 * @return orgFaxNo
	 */
	public String getOrgFaxNo() {
		return orgFaxNo;
	}

	/**
	 * orgFaxNoを設定します。
	 * @param orgFaxNo orgFaxNo
	 */
	public void setOrgFaxNo(String orgFaxNo) {
		this.orgFaxNo = orgFaxNo;
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

