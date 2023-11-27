/*
 * Created on 2009/10/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderslipforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepOrderSlipDetailForReportクラス.
 * @author kanri-user
 */
public class RepOrderSlipDetailForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepOrderSlipDetailForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション orderUnitprice */
	public static final String orderUnitprice_COLUMN = "ORDER_UNITPRICE";

	/** COLUMNアノテーション standardUnitprice */
	public static final String standardUnitprice_COLUMN = "STANDARD_UNITPRICE";

	/** COLUMNアノテーション standardDiscount */
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";

	/** COLUMNアノテーション specialDiscount */
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";

	/** COLUMNアノテーション orderAmount */
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";

	/** COLUMNアノテーション tmpUnitpriceFlg */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";

	/** COLUMNアノテーション tmpUnitpriceString */
	public static final String tmpUnitpriceString_COLUMN = "TMP_UNITPRICE_STRING";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** COLUMNアノテーション estimateStandardAmount */
	public static final String estimateStandardAmount_COLUMN = "ESTIMATE_STANDARD_AMOUNT";

	/** COLUMNアノテーション estimateMatss */
	public static final String estimateMatss_COLUMN = "ESTIMATE_MATSS";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション inputTantoCd */
	public static final String inputTantoCd_COLUMN = "INPUT_TANTO_CD";

	/** COLUMNアノテーション inputTantoName */
	public static final String inputTantoName_COLUMN = "INPUT_TANTO_NAME";

	/** COLUMNアノテーション salesTantoCd */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";

	/** COLUMNアノテーション salesTantoName */
	public static final String salesTantoName_COLUMN = "SALES_TANTO_NAME";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";

	/** COLUMNアノテーション deliveryAddressAll */
	public static final String deliveryAddressAll_COLUMN = "DELIVERY_ADDRESS_ALL";

	/** COLUMNアノテーション deliveryTelNo */
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション shippingStatusCd */
	public static final String shippingStatusCd_COLUMN = "SHIPPING_STATUS_CD";

	/** COLUMNアノテーション purchaseStatusCd */
	public static final String purchaseStatusCd_COLUMN = "PURCHASE_STATUS_CD";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String orderNo;

	private java.math.BigDecimal rowNo;

	private String shippingNo;

	private String itemCd;

	private String itemName;

	private String styleOfPacking;

	private String otherCompanyCd1;

	private java.math.BigDecimal orderQty;

	private java.math.BigDecimal orderUnitprice;

	private java.math.BigDecimal standardUnitprice;

	private java.math.BigDecimal standardDiscount;

	private java.math.BigDecimal specialDiscount;

	private java.math.BigDecimal orderAmount;

	private java.math.BigDecimal tmpUnitpriceFlg;

	private String tmpUnitpriceString;

	private java.math.BigDecimal matss;

	private java.math.BigDecimal estimateStandardAmount;

	private java.math.BigDecimal estimateMatss;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String statusName;

	private java.math.BigDecimal orderDivision;

	private String inputTantoCd;

	private String inputTantoName;

	private String salesTantoCd;

	private String salesTantoName;

	private String deliveryCd;

	private String deliveryName;

	private String deliveryAddressAll;

	private String deliveryTelNo;

	private String venderCd;

	private String venderName;

	private String organizationCd;

	private String organizationName;

	private String shippingStatusCd;

	private String purchaseStatusCd;

	private java.sql.Timestamp scheduledShippingDate;

	private java.sql.Timestamp orderDate;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

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
	 * rowNo取得.
	 * @return rowNo
	 */
	public java.math.BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * rowNo設定.
	 * @param rowNo rowNo
	 */
	public void setRowNo(final java.math.BigDecimal rowNo) {
		this.rowNo = rowNo;
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
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * styleOfPacking取得.
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * otherCompanyCd1取得.
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1設定.
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * orderQty取得.
	 * @return orderQty
	 */
	public java.math.BigDecimal getOrderQty() {
		return this.orderQty;
	}

	/**
	 * orderQty設定.
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final java.math.BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * orderUnitprice取得.
	 * @return orderUnitprice
	 */
	public java.math.BigDecimal getOrderUnitprice() {
		return this.orderUnitprice;
	}

	/**
	 * orderUnitprice設定.
	 * @param orderUnitprice orderUnitprice
	 */
	public void setOrderUnitprice(final java.math.BigDecimal orderUnitprice) {
		this.orderUnitprice = orderUnitprice;
	}

	/**
	 * standardUnitprice取得.
	 * @return standardUnitprice
	 */
	public java.math.BigDecimal getStandardUnitprice() {
		return this.standardUnitprice;
	}

	/**
	 * standardUnitprice設定.
	 * @param standardUnitprice standardUnitprice
	 */
	public void setStandardUnitprice(final java.math.BigDecimal standardUnitprice) {
		this.standardUnitprice = standardUnitprice;
	}

	/**
	 * standardDiscount取得.
	 * @return standardDiscount
	 */
	public java.math.BigDecimal getStandardDiscount() {
		return this.standardDiscount;
	}

	/**
	 * standardDiscount設定.
	 * @param standardDiscount standardDiscount
	 */
	public void setStandardDiscount(final java.math.BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * specialDiscount取得.
	 * @return specialDiscount
	 */
	public java.math.BigDecimal getSpecialDiscount() {
		return this.specialDiscount;
	}

	/**
	 * specialDiscount設定.
	 * @param specialDiscount specialDiscount
	 */
	public void setSpecialDiscount(final java.math.BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
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
	 * tmpUnitpriceFlg取得.
	 * @return tmpUnitpriceFlg
	 */
	public java.math.BigDecimal getTmpUnitpriceFlg() {
		return this.tmpUnitpriceFlg;
	}

	/**
	 * tmpUnitpriceFlg設定.
	 * @param tmpUnitpriceFlg tmpUnitpriceFlg
	 */
	public void setTmpUnitpriceFlg(final java.math.BigDecimal tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
	}

	/**
	 * tmpUnitpriceString取得.
	 * @return tmpUnitpriceString
	 */
	public String getTmpUnitpriceString() {
		return this.tmpUnitpriceString;
	}

	/**
	 * tmpUnitpriceString設定.
	 * @param tmpUnitpriceString tmpUnitpriceString
	 */
	public void setTmpUnitpriceString(final String tmpUnitpriceString) {
		this.tmpUnitpriceString = tmpUnitpriceString;
	}

	/**
	 * matss取得.
	 * @return matss
	 */
	public java.math.BigDecimal getMatss() {
		return this.matss;
	}

	/**
	 * matss設定.
	 * @param matss matss
	 */
	public void setMatss(final java.math.BigDecimal matss) {
		this.matss = matss;
	}

	/**
	 * estimateStandardAmount取得.
	 * @return estimateStandardAmount
	 */
	public java.math.BigDecimal getEstimateStandardAmount() {
		return this.estimateStandardAmount;
	}

	/**
	 * estimateStandardAmount設定.
	 * @param estimateStandardAmount estimateStandardAmount
	 */
	public void setEstimateStandardAmount(final java.math.BigDecimal estimateStandardAmount) {
		this.estimateStandardAmount = estimateStandardAmount;
	}

	/**
	 * estimateMatss取得.
	 * @return estimateMatss
	 */
	public java.math.BigDecimal getEstimateMatss() {
		return this.estimateMatss;
	}

	/**
	 * estimateMatss設定.
	 * @param estimateMatss estimateMatss
	 */
	public void setEstimateMatss(final java.math.BigDecimal estimateMatss) {
		this.estimateMatss = estimateMatss;
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
	 * statusName取得.
	 * @return statusName
	 */
	public String getStatusName() {
		return this.statusName;
	}

	/**
	 * statusName設定.
	 * @param statusName statusName
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
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
	 * shippingStatusCd取得.
	 * @return shippingStatusCd
	 */
	public String getShippingStatusCd() {
		return this.shippingStatusCd;
	}

	/**
	 * shippingStatusCd設定.
	 * @param shippingStatusCd shippingStatusCd
	 */
	public void setShippingStatusCd(final String shippingStatusCd) {
		this.shippingStatusCd = shippingStatusCd;
	}

	/**
	 * purchaseStatusCd取得.
	 * @return purchaseStatusCd
	 */
	public String getPurchaseStatusCd() {
		return this.purchaseStatusCd;
	}

	/**
	 * purchaseStatusCd設定.
	 * @param purchaseStatusCd purchaseStatusCd
	 */
	public void setPurchaseStatusCd(final String purchaseStatusCd) {
		this.purchaseStatusCd = purchaseStatusCd;
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

