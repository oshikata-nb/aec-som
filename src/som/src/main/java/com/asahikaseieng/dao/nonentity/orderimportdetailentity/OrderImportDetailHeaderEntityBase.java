/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportdetailentity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderImportListクラス.
 * @author
 */
public class OrderImportDetailHeaderEntityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportDetailHeaderEntityBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション frstOrderNo */
	public static final String frstOrderNo_COLUMN = "FRST_ORDER_NO";
	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";
	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";
	/** COLUMNアノテーション venderGroupCd */
	public static final String venderGroupCd_COLUMN = "VENDER_GROUP_CD";
	/** COLUMNアノテーション venderGroupName */
	public static final String venderGroupName_COLUMN = "VENDER_GROUP_NAME";
	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";
	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";
	/** COLUMNアノテーション deliveryName */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";
	/** COLUMNアノテーション address */
	public static final String address_COLUMN = "ADDRESS";
	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";
	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";
	/** COLUMNアノテーション leadTime */
	public static final String leadTime_COLUMN = "LEAD_TIME";
	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";
	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";
	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";
	/** COLUMNアノテーション suggestedDeliverylimit */
	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";
	/** COLUMNアノテーション orderAmount */
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";
	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";
	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";
	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";
	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";
	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";
	/** COLUMNアノテーション carryFare */
	public static final String carryFare_COLUMN = "CARRY_FARE";
	/** COLUMNアノテーション carryInvoiceFlag */
	public static final String carryInvoiceFlag_COLUMN = "CARRY_INVOICE_FLAG";
	/** COLUMNアノテーション printSummery */
	public static final String printSummery_COLUMN = "PRINT_SUMMERY";
	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";
	/** COLUMNアノテーション orderPicture */
	public static final String orderPicture_COLUMN = "ORDER_PICTURE";
	/** COLUMNアノテーション orderSummery */
	public static final String orderSummery_COLUMN = "ORDER_SUMMERY";
	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	/** COLUMNアノテーション deliverydateContactSummery */
	public static final String deliverydateContactSummery_COLUMN = "DELIVERYDATE_CONTACT_SUMMERY";
	/** COLUMNアノテーション salesTantoCd */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";
	/** COLUMNアノテーション delFlg */
	public static final String delFlg_COLUMN = "DEL_FLG";
	/** COLUMNアノテーション cancelFlg */
	public static final String cancelFlg_COLUMN = "CANCEL_FLG";
	/** COLUMNアノテーション importStatus */
	public static final String importStatus_COLUMN = "IMPORT_STATUS";

	/** COLUMNアノテーション customerOrderNo */
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";

	/** COLUMNアノテーション deliveryTelNo */
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";

	// インスタンスフィールド
	//
	private String frstOrderNo;
	private String orderNo;
	private BigDecimal orderDivision;
	private String venderGroupCd;
	private String venderGroupName;
	private Timestamp orderDate;
	private String deliveryCd;
	private String deliveryName;
	private String address;
	private String deliveryAddress;
	private Timestamp scheduledShippingDate;
	private BigDecimal leadTime;
	private String carryCd;
	private Timestamp deliveryExpectedDate;
	private String deliveryExpectedTime;
	private Timestamp suggestedDeliverlimit;
	private BigDecimal orderAmount;
	private String balanceCd;
	private String organizationCd;
	private String organizationName;
	private String venderCd;
	private String venderName;
	private BigDecimal carryFare;
	private BigDecimal carryInvoiceFlag;
	private String printSummery;
	private String deliverySlipSummery;
	private String orderPicture;
	private String orderSummery;
	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	private String deliverydateContactSummery;
	private String salesTantoCd;
	private BigDecimal delFlg;
	private BigDecimal cancelFlg;
	private String importStatus;
	private String customerOrderNo;
	private String deliveryTelNo;

	/**
	 * frstOrderNoを取得します。
	 * @return frstOrderNo
	 */
	public String getFrstOrderNo() {
		return frstOrderNo;
	}

	/**
	 * orderNoを取得します。
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * orderDivisionを取得します。
	 * @return orderDivision
	 */
	public BigDecimal getOrderDivision() {
		return orderDivision;
	}

	/**
	 * venderGroupCdを取得します。
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return venderGroupCd;
	}

	/**
	 * venderGroupNameを取得します。
	 * @return venderGroupName
	 */
	public String getVenderGroupName() {
		return venderGroupName;
	}

	/**
	 * orderDateを取得します。
	 * @return orderDate
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryNameを取得します。
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * addressを取得します。
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * deliveryAddressを取得します。
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * leadTimeを取得します。
	 * @return leadTime
	 */
	public BigDecimal getLeadTime() {
		return leadTime;
	}

	/**
	 * carryCdを取得します。
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * deliveryExpectedDateを取得します。
	 * @return deliveryExpectedDate
	 */
	public Timestamp getDeliveryExpectedDate() {
		return deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedTimeを取得します。
	 * @return deliveryExpectedTime
	 */
	public String getDeliveryExpectedTime() {
		return deliveryExpectedTime;
	}

	/**
	 * suggestedDeliverlimitを取得します。
	 * @return suggestedDeliverlimit
	 */
	public Timestamp getSuggestedDeliverlimit() {
		return suggestedDeliverlimit;
	}

	/**
	 * orderAmountを取得します。
	 * @return orderAmount
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderNameを取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * carryFareを取得します。
	 * @return carryFare
	 */
	public BigDecimal getCarryFare() {
		return carryFare;
	}

	/**
	 * carryInvoiceFlagを取得します。
	 * @return carryInvoiceFlag
	 */
	public BigDecimal getCarryInvoiceFlag() {
		return carryInvoiceFlag;
	}

	/**
	 * printSummeryを取得します。
	 * @return printSummery
	 */
	public String getPrintSummery() {
		return printSummery;
	}

	/**
	 * deliverySlipSummeryを取得します。
	 * @return deliverySlipSummery
	 */
	public String getDeliverySlipSummery() {
		return deliverySlipSummery;
	}

	/**
	 * orderPictureを取得します。
	 * @return orderPicture
	 */
	public String getOrderPicture() {
		return orderPicture;
	}

	/**
	 * orderSummeryを取得します。
	 * @return orderSummery
	 */
	public String getOrderSummery() {
		return orderSummery;
	}

	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	/**
	 * deliverydateContactSummeryを取得します。
	 * @return deliverydateContactSummery
	 */
	public String getDeliverydateContactSummery() {
		return deliverydateContactSummery;
	}

	/**
	 * salesTantoCdを取得します。
	 * @return salesTantoCd
	 */
	public String getSalesTantoCd() {
		return salesTantoCd;
	}

	/**
	 * frstOrderNoを設定します。
	 * @param frstOrderNo frstOrderNo
	 */
	public void setFrstOrderNo(String frstOrderNo) {
		this.frstOrderNo = frstOrderNo;
	}

	/**
	 * orderNoを設定します。
	 * @param orderNo orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * orderDivisionを設定します。
	 * @param orderDivision orderDivision
	 */
	public void setOrderDivision(BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * venderGroupCdを設定します。
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}

	/**
	 * venderGroupNameを設定します。
	 * @param venderGroupName venderGroupName
	 */
	public void setVenderGroupName(String venderGroupName) {
		this.venderGroupName = venderGroupName;
	}

	/**
	 * orderDateを設定します。
	 * @param orderDate orderDate
	 */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryNameを設定します。
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * addressを設定します。
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * deliveryAddressを設定します。
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * leadTimeを設定します。
	 * @param leadTime leadTime
	 */
	public void setLeadTime(BigDecimal leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * carryCdを設定します。
	 * @param carryCd carryCd
	 */
	public void setCarryCd(String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * deliveryExpectedDateを設定します。
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(Timestamp deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedTimeを設定します。
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * suggestedDeliverlimitを設定します。
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(Timestamp suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
	}

	/**
	 * orderAmountを設定します。
	 * @param orderAmount orderAmount
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderNameを設定します。
	 * @param venderName venderName
	 */
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	/**
	 * carryFareを設定します。
	 * @param carryFare carryFare
	 */
	public void setCarryFare(BigDecimal carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * carryInvoiceFlagを設定します。
	 * @param carryInvoiceFlag carryInvoiceFlag
	 */
	public void setCarryInvoiceFlag(BigDecimal carryInvoiceFlag) {
		this.carryInvoiceFlag = carryInvoiceFlag;
	}

	/**
	 * printSummeryを設定します。
	 * @param printSummery printSummery
	 */
	public void setPrintSummery(String printSummery) {
		this.printSummery = printSummery;
	}

	/**
	 * deliverySlipSummeryを設定します。
	 * @param deliverySlipSummery deliverySlipSummery
	 */
	public void setDeliverySlipSummery(String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
	}

	/**
	 * orderPictureを設定します。
	 * @param orderPicture orderPicture
	 */
	public void setOrderPicture(String orderPicture) {
		this.orderPicture = orderPicture;
	}

	/**
	 * orderSummeryを設定します。
	 * @param orderSummery orderSummery
	 */
	public void setOrderSummery(String orderSummery) {
		this.orderSummery = orderSummery;
	}

	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	/**
	 * deliverydateContactSummeryを設定します。
	 * @param deliverydateContactSummery deliverydateContactSummery
	 */
	public void setDeliverydateContactSummery(String deliverydateContactSummery) {
		this.deliverydateContactSummery = deliverydateContactSummery;
	}

	/**
	 * salesTantoCdを設定します。
	 * @param salesTantoCd salesTantoCd
	 */
	public void setSalesTantoCd(String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * scheduledShippingDateを取得します。
	 * @return scheduledShippingDate
	 */
	public Timestamp getScheduledShippingDate() {
		return scheduledShippingDate;
	}

	/**
	 * scheduledShippingDateを設定します。
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(Timestamp scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}

	/**
	 * delFlgを取得します。
	 * @return delFlg
	 */
	public BigDecimal getDelFlg() {
		return delFlg;
	}

	/**
	 * cancelFlgを取得します。
	 * @return cancelFlg
	 */
	public BigDecimal getCancelFlg() {
		return cancelFlg;
	}

	/**
	 * delFlgを設定します。
	 * @param delFlg delFlg
	 */
	public void setDelFlg(BigDecimal delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * cancelFlgを設定します。
	 * @param cancelFlg cancelFlg
	 */
	public void setCancelFlg(BigDecimal cancelFlg) {
		this.cancelFlg = cancelFlg;
	}

	/**
	 * importStatusを取得します。
	 * @return importStatus
	 */
	public String getImportStatus() {
		return importStatus;
	}

	/**
	 * importStatusを設定します。
	 * @param importStatus importStatus
	 */
	public void setImportStatus(String importStatus) {
		this.importStatus = importStatus;
	}

	/**
	 * customerOrderNoを取得します。
	 * @return customerOrderNo
	 */
	public String getCustomerOrderNo() {
		return customerOrderNo;
	}

	/**
	 * customerOrderNoを設定します。
	 * @param customerOrderNo customerOrderNo
	 */
	public void setCustomerOrderNo(String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
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

