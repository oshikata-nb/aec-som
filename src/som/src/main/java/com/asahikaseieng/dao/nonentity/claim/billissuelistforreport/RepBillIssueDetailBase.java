/*
 * Created on 2015/11/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.billissuelistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RepBillIssueDetailクラス.
 * @author Administrator
 */
public class RepBillIssueDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepBillIssueDetailBase() {
	}

	//
	// 定数
	//

	/*  */
	/** COLUMNアノテーション claimNo */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/*  */
	/** COLUMNアノテーション headVenderCd */
	public static final String headVenderCd_COLUMN = "HEAD_VENDER_CD";

	/*  */
	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/*  */
	/** COLUMNアノテーション tranVenderCd */
	public static final String tranVenderCd_COLUMN = "TRAN_VENDER_CD";

	/*  */
	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/*  */
	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/*  */
	/** COLUMNアノテーション tranDate */
	public static final String tranDate_COLUMN = "TRAN_DATE";

	/*  */
	/** COLUMNアノテーション customerOrderNo */
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";

	/*  */
	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/*  */
	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/*  */
	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/*  */
	/** COLUMNアノテーション quantity */
	public static final String quantity_COLUMN = "QUANTITY";

	/*  */
	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/*  */
	/** COLUMNアノテーション unitprice */
	public static final String unitprice_COLUMN = "UNITPRICE";

	/*  */
	/** COLUMNアノテーション amount */
	public static final String amount_COLUMN = "AMOUNT";

	/*  */
	/** COLUMNアノテーション taxDisplayReport */
	public static final String taxDisplayReport_COLUMN = "TAX_DISPLAY_REPORT";

	/*  */
	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/*  */
	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/*  */
	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/*  */
	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/*  */
	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/*  */
	/** COLUMNアノテーション salesSlipNo */
	public static final String salesSlipNo_COLUMN = "SALES_SLIP_NO";

	/*  */
	/** COLUMNアノテーション salesSlipRowNo */
	public static final String salesSlipRowNo_COLUMN = "SALES_SLIP_ROW_NO";

	/*  */
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/*  */
	/** COLUMNアノテーション estimateMatss */
	public static final String estimateMatss_COLUMN = "ESTIMATE_MATSS";

	/*  */
	/** COLUMNアノテーション estimateUnit */
	public static final String estimateUnit_COLUMN = "ESTIMATE_UNIT";

	/*  */
	/** COLUMNアノテーション estimateUnitprice */
	public static final String estimateUnitprice_COLUMN = "ESTIMATE_UNITPRICE";

	/*  */
	/** COLUMNアノテーション estimateAmount */
	public static final String estimateAmount_COLUMN = "ESTIMATE_AMOUNT";

	/*  */
	/** COLUMNアノテーション estimateTaxAmount */
	public static final String estimateTaxAmount_COLUMN = "ESTIMATE_TAX_AMOUNT";

	/*  */
	/** COLUMNアノテーション slipPbulishCompAmount */
	public static final String slipPbulishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/*  */
	/** COLUMNアノテーション invoicePtnAmount */
	public static final String invoicePtn_COLUMN = "INVOICE_PTN";

	//
	// インスタンスフィールド
	//

	/**  */
	private String claimNo;

	/**  */
	private String headVenderCd;

	/**  */
	private java.sql.Timestamp creditDate;

	/**  */
	private String tranVenderCd;

	/**  */
	private String venderName1;

	/**  */
	private String venderName2;

	/**  */
	private java.sql.Timestamp tranDate;

	/**  */
	private String customerOrderNo;

	/**  */
	private String categoryName;

	/**  */
	private String itemCd;

	/**  */
	private String styleOfPacking;

	/**  */
	private java.math.BigDecimal quantity;

	/**  */
	private String unit;

	/**  */
	private java.math.BigDecimal unitprice;

	/**  */
	private java.math.BigDecimal amount;

	/**  */
	private String taxDisplayReport;

	/**  */
	private java.math.BigDecimal taxAmount;

	/**  */
	private String remark;

	/**  */
	private String deliveryAddress;

	/**  */
	private String slipNo;

	/**  */
	private java.math.BigDecimal rowNo;

	/**  */
	private String salesSlipNo;

	/**  */
	private java.math.BigDecimal salesSlipRowNo;

	/**  */
	private String itemName;

	/**  */
	private java.math.BigDecimal estimateMatss;

	/**  */
	private String estimateUnit;

	/**  */
	private java.math.BigDecimal estimateUnitprice;

	/**  */
	private java.math.BigDecimal estimateAmount;

	/**  */
	private java.math.BigDecimal estimateTaxAmount;

	/**  */
	private java.math.BigDecimal slipPbulishComp;

	/**  */
	private  java.math.BigDecimal invoicePtn;

	//
	// インスタンスメソッド
	//

	/**
	 * claimNo取得.
	 *
	 * @return claimNo
	 */
	public String getClaimNo() {
		return this.claimNo;
	}

	/**
	 * claimNo設定.
	 *
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * headVenderCd取得.
	 *
	 * @return headVenderCd
	 */
	public String getHeadVenderCd() {
		return this.headVenderCd;
	}

	/**
	 * headVenderCd設定.
	 *
	 * @param headVenderCd headVenderCd
	 */
	public void setHeadVenderCd(final String headVenderCd) {
		this.headVenderCd = headVenderCd;
	}

	/**
	 * creditDate取得.
	 *
	 * @return creditDate
	 */
	public java.sql.Timestamp getCreditDate() {
		return this.creditDate;
	}

	/**
	 * creditDate設定.
	 *
	 * @param creditDate creditDate
	 */
	public void setCreditDate(final java.sql.Timestamp creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * tranVenderCd取得.
	 *
	 * @return tranVenderCd
	 */
	public String getTranVenderCd() {
		return this.tranVenderCd;
	}

	/**
	 * tranVenderCd設定.
	 *
	 * @param tranVenderCd tranVenderCd
	 */
	public void setTranVenderCd(final String tranVenderCd) {
		this.tranVenderCd = tranVenderCd;
	}

	/**
	 * venderName1取得.
	 *
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 *
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderName2取得.
	 *
	 * @return venderName2
	 */
	public String getVenderName2() {
		return this.venderName2;
	}

	/**
	 * venderName2設定.
	 *
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * tranDate取得.
	 *
	 * @return tranDate
	 */
	public java.sql.Timestamp getTranDate() {
		return this.tranDate;
	}

	/**
	 * tranDate設定.
	 *
	 * @param tranDate tranDate
	 */
	public void setTranDate(final java.sql.Timestamp tranDate) {
		this.tranDate = tranDate;
	}

	/**
	 * customerOrderNo取得.
	 *
	 * @return customerOrderNo
	 */
	public String getCustomerOrderNo() {
		return this.customerOrderNo;
	}

	/**
	 * customerOrderNo設定.
	 *
	 * @param customerOrderNo customerOrderNo
	 */
	public void setCustomerOrderNo(final String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	/**
	 * categoryName取得.
	 *
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * categoryName設定.
	 *
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * itemCd取得.
	 *
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 *
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * styleOfPacking取得.
	 *
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 *
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * quantity取得.
	 *
	 * @return quantity
	 */
	public java.math.BigDecimal getQuantity() {
		return this.quantity;
	}

	/**
	 * quantity設定.
	 *
	 * @param quantity quantity
	 */
	public void setQuantity(final java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * unit取得.
	 *
	 * @return unit
	 */
	public String getUnit() {
		return this.unit;
	}

	/**
	 * unit設定.
	 *
	 * @param unit unit
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	/**
	 * unitprice取得.
	 *
	 * @return unitprice
	 */
	public java.math.BigDecimal getUnitprice() {
		return this.unitprice;
	}

	/**
	 * unitprice設定.
	 *
	 * @param unitprice unitprice
	 */
	public void setUnitprice(final java.math.BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	/**
	 * amount取得.
	 *
	 * @return amount
	 */
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * amount設定.
	 *
	 * @param amount amount
	 */
	public void setAmount(final java.math.BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * taxDisplayReport取得.
	 *
	 * @return taxDisplayReport
	 */
	public String getTaxDisplayReport() {
		return this.taxDisplayReport;
	}

	/**
	 * taxDisplayReport設定.
	 *
	 * @param taxDisplayReport taxDisplayReport
	 */
	public void setTaxDisplayReport(final String taxDisplayReport) {
		this.taxDisplayReport = taxDisplayReport;
	}

	/**
	 * taxAmount取得.
	 *
	 * @return taxAmount
	 */
	public java.math.BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * taxAmount設定.
	 *
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * remark取得.
	 *
	 * @return remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * remark設定.
	 *
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * deliveryAddress取得.
	 *
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * deliveryAddress設定.
	 *
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * slipNo取得.
	 *
	 * @return slipNo
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * slipNo設定.
	 *
	 * @param slipNo slipNo
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * rowNo取得.
	 *
	 * @return rowNo
	 */
	public java.math.BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * rowNo設定.
	 *
	 * @param rowNo rowNo
	 */
	public void setRowNo(final java.math.BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * salesSlipNo取得.
	 *
	 * @return salesSlipNo
	 */
	public String getSalesSlipNo() {
		return this.salesSlipNo;
	}

	/**
	 * salesSlipNo設定.
	 *
	 * @param salesSlipNo salesSlipNo
	 */
	public void setSalesSlipNo(final String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}

	/**
	 * salesSlipRowNo取得.
	 *
	 * @return salesSlipRowNo
	 */
	public java.math.BigDecimal getSalesSlipRowNo() {
		return this.salesSlipRowNo;
	}

	/**
	 * salesSlipRowNo設定.
	 *
	 * @param salesSlipRowNo salesSlipRowNo
	 */
	public void setSalesSlipRowNo(final java.math.BigDecimal salesSlipRowNo) {
		this.salesSlipRowNo = salesSlipRowNo;
	}

	/**
	 * itemName取得.
	 *
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 *
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * estimateMatss取得.
	 *
	 * @return estimateMatss
	 */
	public java.math.BigDecimal getEstimateMatss() {
		return this.estimateMatss;
	}

	/**
	 * estimateMatss設定.
	 *
	 * @param estimateMatss estimateMatss
	 */
	public void setEstimateMatss(final java.math.BigDecimal estimateMatss) {
		this.estimateMatss = estimateMatss;
	}

	/**
	 * estimateUnit取得.
	 *
	 * @return estimateUnit
	 */
	public String getEstimateUnit() {
		return this.estimateUnit;
	}

	/**
	 * estimateUnit設定.
	 *
	 * @param estimateUnit estimateUnit
	 */
	public void setEstimateUnit(final String estimateUnit) {
		this.estimateUnit = estimateUnit;
	}

	/**
	 * estimateUnitprice取得.
	 *
	 * @return estimateUnitprice
	 */
	public java.math.BigDecimal getEstimateUnitprice() {
		return this.estimateUnitprice;
	}

	/**
	 * estimateUnitprice設定.
	 *
	 * @param estimateUnitprice estimateUnitprice
	 */
	public void setEstimateUnitprice(final java.math.BigDecimal estimateUnitprice) {
		this.estimateUnitprice = estimateUnitprice;
	}

	/**
	 * estimateAmount取得.
	 *
	 * @return estimateAmount
	 */
	public java.math.BigDecimal getEstimateAmount() {
		return this.estimateAmount;
	}

	/**
	 * estimateAmount設定.
	 *
	 * @param estimateAmount estimateAmount
	 */
	public void setEstimateAmount(final java.math.BigDecimal estimateAmount) {
		this.estimateAmount = estimateAmount;
	}

	/**
	 * estimateTaxAmount取得.
	 *
	 * @return estimateTaxAmount
	 */
	public java.math.BigDecimal getEstimateTaxAmount() {
		return this.estimateTaxAmount;
	}

	/**
	 * estimateTaxAmount設定.
	 *
	 * @param estimateTaxAmount estimateTaxAmount
	 */
	public void setEstimateTaxAmount(final java.math.BigDecimal estimateTaxAmount) {
		this.estimateTaxAmount = estimateTaxAmount;
	}

	/**
	 * slipPbulishComp取得.
	 *
	 * @return slipPbulishComp
	 */
	public java.math.BigDecimal getSlipPbulishComp() {
		return this.slipPbulishComp;
	}

	/**
	 * estimateTaxAmount設定.
	 *
	 * @param slipPbulishComp slipPbulishComp
	 */
	public void setSlipPbulishComp(final java.math.BigDecimal slipPbulishComp) {
		this.slipPbulishComp = slipPbulishComp;
	}

	/**
	 * invoicePtn取得.
	 *
	 * @return invoicePtn
	 */
	public java.math.BigDecimal getInvoicePtn() {
		return this.invoicePtn;
	}

	/**
	 * invoicePtn設定.
	 *
	 * @param invoicePtn invoicePtn
	 */
	public void setInvoicePtn(final java.math.BigDecimal invoicePtn) {
		this.invoicePtn = invoicePtn;
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

