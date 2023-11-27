/*
 * Created on Tue Feb 17 09:59:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.frstorderhead;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderHeadBaseクラス.
 * @author kanri-user
 */
public class FrstOrderHeadBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FrstOrderHeadBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "FRST_ORDER_HEAD";

	// /** IDアノテーション frstOrderNo. */
	// public static final String frstOrderNo_ID = "assigned";

	/** frstOrderNo_COLUMN */
	public static final String frstOrderNo_COLUMN = "FRST_ORDER_NO";

	public static final String orderNo_COLUMN = "ORDER_NO";

	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	public static final String orderDate_COLUMN = "ORDER_DATE";

	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	public static final String inputTantoCd_COLUMN = "INPUT_TANTO_CD";

	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";

	public static final String venderCd_COLUMN = "VENDER_CD";

	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";

	public static final String balanceCd_COLUMN = "BALANCE_CD";

	public static final String status_COLUMN = "STATUS";

	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";

	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";

	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";

	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	public static final String leadTime_COLUMN = "LEAD_TIME";

	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";

	public static final String carryCd_COLUMN = "CARRY_CD";

	public static final String carryFare_COLUMN = "CARRY_FARE";

	public static final String carryInvoiceFlag_COLUMN = "CARRY_INVOICE_FLAG";

	public static final String orderPicture_COLUMN = "ORDER_PICTURE";

	public static final String printSummery_COLUMN = "PRINT_SUMMERY";

	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";

	public static final String orderSummery_COLUMN = "ORDER_SUMMERY";

	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	public static final String deliverydateContactSummery_COLUMN = "DELIVERYDATE_CONTACT_SUMMERY";

	public static final String venderGroupCd_COLUMN = "VENDER_GROUP_CD";

	public static final String totalWeight_COLUMN = "TOTAL_WEIGHT";

	public static final String delFlg_COLUMN = "DEL_FLG";

	public static final String cancelFlg_COLUMN = "CANCEL_FLG";

	public static final String inputDate_COLUMN = "INPUT_DATE";

	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	public static final String updateDate_COLUMN = "UPDATE_DATE";

	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	public String frstOrderNo;

	public String orderNo;

	public BigDecimal orderDivision;

	public Timestamp orderDate;

	public String organizationCd;

	public String inputTantoCd;

	public String salesTantoCd;

	public String venderCd;

	public String deliveryCd;

	public String deliveryAddress;

	public String deliveryTelNo;

	public String balanceCd;

	public BigDecimal status;

	public String customerOrderNo;

	public BigDecimal orderAmount;

	public Timestamp suggestedDeliverlimit;

	public Timestamp scheduledShippingDate;

	public BigDecimal leadTime;

	public Timestamp deliveryExpectedDate;

	public String deliveryExpectedTime;

	public String carryCd;

	public BigDecimal carryFare;

	public BigDecimal carryInvoiceFlag;

	/** orderPicture */
	public String orderPicture;

	public String printSummery;

	public String deliverySlipSummery;

	public String orderSummery;

	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	public String deliverydateContactSummery;

	public String venderGroupCd;

	public BigDecimal totalWeight;

	public BigDecimal delFlg;

	public BigDecimal cancelFlg;

	public BigDecimal invisibleFlg;

	public Timestamp inputDate;

	public String inputorCd;

	public Timestamp updateDate;

	public String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * frstOrderNoを取得します。
	 * @return frstOrderNo
	 */
	public String getFrstOrderNo() {
		return frstOrderNo;
	}

	/**
	 * frstOrderNoを設定します。
	 * @param frstOrderNo frstOrderNo
	 */
	public void setFrstOrderNo(String frstOrderNo) {
		this.frstOrderNo = frstOrderNo;
	}

	/**
	 * orderNoを取得します。
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * orderNoを設定します。
	 * @param orderNo orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * orderDivisionを取得します。
	 * @return orderDivision
	 */
	public BigDecimal getOrderDivision() {
		return orderDivision;
	}

	/**
	 * orderDivisionを設定します。
	 * @param orderDivision orderDivision
	 */
	public void setOrderDivision(BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * inputTantoCdを取得します。
	 * @return inputTantoCd
	 */
	public String getInputTantoCd() {
		return inputTantoCd;
	}

	/**
	 * inputTantoCdを設定します。
	 * @param inputTantoCd inputTantoCd
	 */
	public void setInputTantoCd(String inputTantoCd) {
		this.inputTantoCd = inputTantoCd;
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
	public void setSalesTantoCd(String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(String venderCd) {
		this.venderCd = venderCd;
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
	 * deliveryAddressを取得します。
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * deliveryAddressを設定します。
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public BigDecimal getStatus() {
		return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(BigDecimal status) {
		this.status = status;
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
	 * orderAmountを取得します。
	 * @return orderAmount
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * orderAmountを設定します。
	 * @param orderAmount orderAmount
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * suggestedDeliverlimitを取得します。
	 * @return suggestedDeliverlimit
	 */
	public Timestamp getSuggestedDeliverlimit() {
		return suggestedDeliverlimit;
	}

	/**
	 * suggestedDeliverlimitを設定します。
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(Timestamp suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
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
	 * leadTimeを取得します。
	 * @return leadTime
	 */
	public BigDecimal getLeadTime() {
		return leadTime;
	}

	/**
	 * leadTimeを設定します。
	 * @param leadTime leadTime
	 */
	public void setLeadTime(BigDecimal leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * deliveryExpectedDateを取得します。
	 * @return deliveryExpectedDate
	 */
	public Timestamp getDeliveryExpectedDate() {
		return deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDateを設定します。
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(Timestamp deliveryExpectedDate) {
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
	 * carryFareを取得します。
	 * @return carryFare
	 */
	public BigDecimal getCarryFare() {
		return carryFare;
	}

	/**
	 * carryFareを設定します。
	 * @param carryFare carryFare
	 */
	public void setCarryFare(BigDecimal carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * carryInvoiceFlagを取得します。
	 * @return carryInvoiceFlag
	 */
	public BigDecimal getCarryInvoiceFlag() {
		return carryInvoiceFlag;
	}

	/**
	 * carryInvoiceFlagを設定します。
	 * @param carryInvoiceFlag carryInvoiceFlag
	 */
	public void setCarryInvoiceFlag(BigDecimal carryInvoiceFlag) {
		this.carryInvoiceFlag = carryInvoiceFlag;
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
	public void setOrderPicture(String orderPicture) {
		this.orderPicture = orderPicture;
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

	/**
	 * deliverySlipSummeryを取得します。
	 * @return deliverySlipSummery
	 */
	public String getDeliverySlipSummery() {
		return deliverySlipSummery;
	}

	/**
	 * deliverySlipSummeryを設定します。
	 * @param deliverySlipSummery deliverySlipSummery
	 */
	public void setDeliverySlipSummery(String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
	}

	/**
	 * orderSummeryを取得します。
	 * @return orderSummery
	 */
	public String getOrderSummery() {
		return orderSummery;
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
	 * deliverydateContactSummeryを取得します。
	 * @return deliverydateContactSummery
	 */
	public String getDeliverydateContactSummery() {
		return deliverydateContactSummery;
	}

	/**
	 * deliverydateContactSummeryを設定します。
	 * @param deliverydateContactSummery deliverydateContactSummery
	 */
	public void setDeliverydateContactSummery(String deliverydateContactSummery) {
		this.deliverydateContactSummery = deliverydateContactSummery;
	}

	/**
	 * venderGroupCdを取得します。
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return venderGroupCd;
	}

	/**
	 * venderGroupCdを設定します。
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}

	/**
	 * delFlgを取得します。
	 * @return delFlg
	 */
	public BigDecimal getDelFlg() {
		return delFlg;
	}

	/**
	 * delFlgを設定します。
	 * @param delFlg delFlg
	 */
	public void setDelFlg(BigDecimal delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * cancelFlgを取得します。
	 * @return cancelFlg
	 */
	public BigDecimal getCancelFlg() {
		return cancelFlg;
	}

	/**
	 * cancelFlgを設定します。
	 * @param cancelFlg cancelFlg
	 */
	public void setCancelFlg(BigDecimal cancelFlg) {
		this.cancelFlg = cancelFlg;
	}

	/**
	 * invisibleFlgを取得します。
	 * @return invisibleFlg
	 */
	public BigDecimal getInvisibleFlg() {
		return invisibleFlg;
	}

	/**
	 * invisibleFlgを設定します。
	 * @param invisibleFlg invisibleFlg
	 */
	public void setInvisibleFlg(BigDecimal invisibleFlg) {
		this.invisibleFlg = invisibleFlg;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(String updatorCd) {
		this.updatorCd = updatorCd;
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
	public void setDeliveryTelNo(final String deliveryTelNo) {
		this.deliveryTelNo = deliveryTelNo;
	}

	/**
	 * orderDateを取得します。
	 * @return orderDate
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * orderDateを設定します。
	 * @param orderDate orderDate
	 */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * totalWeightを取得します。
	 * @return totalWeight
	 */
	public BigDecimal getTotalWeight() {
		return totalWeight;
	}

	/**
	 * totalWeightを設定します。
	 * @param totalWeight totalWeight
	 */
	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}
}
