/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apbalanceforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ApBalanceListForReportクラス.
 * @author t0011036
 */
public class ApBalanceListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApBalanceListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション payableNo */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション supplierCd */
	public static final String supplierCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション payableDate */
	public static final String payableDate_COLUMN = "PAYABLE_DATE";

	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション paymentAmount */
	public static final String paymentAmount_COLUMN = "PAYMENT_AMOUNT";

	/** COLUMNアノテーション otherPaymentAmount */
	public static final String otherPaymentAmount_COLUMN = "OTHER_PAYMENT_AMOUNT";

	/** COLUMNアノテーション stockingReturnedAmount */
	public static final String stockingReturnedAmount_COLUMN = "STOCKING_RETURNED_AMOUNT";

	/** COLUMNアノテーション stockingDiscountAmount */
	public static final String stockingDiscountAmount_COLUMN = "STOCKING_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション otherStockingAmount */
	public static final String otherStockingAmount_COLUMN = "OTHER_STOCKING_AMOUNT";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション payableAmount */
	public static final String payableAmount_COLUMN = "PAYABLE_AMOUNT";

	/** COLUMNアノテーション accountPayableBreakdown */
	public static final String accountPayableBreakdown_COLUMN = "ACCOUNT_PAYABLE_BREAKDOWN";

	/** COLUMNアノテーション arrearageBreakdown */
	public static final String arrearageBreakdown_COLUMN = "ARREARAGE_BREAKDOWN";

	/** COLUMNアノテーション exceptBreakdown */
	public static final String exceptBreakdown_COLUMN = "EXCEPT_BREAKDOWN";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション totalPayment */
	public static final String totalPayment_COLUMN = "TOTAL_PAYMENT";

	/** COLUMNアノテーション otherStocking */
	public static final String otherStocking_COLUMN = "OTHER_STOCKING";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String payableNo;

	private String organizationCd;

	private String supplierCd;

	private java.sql.Timestamp payableDate;

	private java.math.BigDecimal balanceForward;

	private java.math.BigDecimal stockingAmount;

	private java.math.BigDecimal paymentAmount;

	private java.math.BigDecimal otherPaymentAmount;

	private java.math.BigDecimal stockingReturnedAmount;

	private java.math.BigDecimal stockingDiscountAmount;

	private java.math.BigDecimal otherStockingAmount;

	private java.math.BigDecimal offsetAmount;

	private java.math.BigDecimal taxAmount;

	private java.math.BigDecimal payableAmount;

	private java.math.BigDecimal accountPayableBreakdown;

	private java.math.BigDecimal arrearageBreakdown;

	private java.math.BigDecimal exceptBreakdown;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String organizationName;

	private String venderName1;

	private String venderShortedName;

	private java.math.BigDecimal totalPayment;

	private java.math.BigDecimal otherStocking;

	private String inputorName;

	private String updatorName;

	//
	// インスタンスメソッド
	//

	/**
	 * payableNo取得.
	 * @return payableNo
	 */
	public String getPayableNo() {
		return this.payableNo;
	}

	/**
	 * payableNo設定.
	 * @param payableNo payableNo
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
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
	 * supplierCd取得.
	 * @return supplierCd
	 */
	public String getSupplierCd() {
		return this.supplierCd;
	}

	/**
	 * supplierCd設定.
	 * @param supplierCd supplierCd
	 */
	public void setSupplierCd(final String supplierCd) {
		this.supplierCd = supplierCd;
	}

	/**
	 * payableDate取得.
	 * @return payableDate
	 */
	public java.sql.Timestamp getPayableDate() {
		return this.payableDate;
	}

	/**
	 * payableDate設定.
	 * @param payableDate payableDate
	 */
	public void setPayableDate(final java.sql.Timestamp payableDate) {
		this.payableDate = payableDate;
	}

	/**
	 * balanceForward取得.
	 * @return balanceForward
	 */
	public java.math.BigDecimal getBalanceForward() {
		return this.balanceForward;
	}

	/**
	 * balanceForward設定.
	 * @param balanceForward balanceForward
	 */
	public void setBalanceForward(final java.math.BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * stockingAmount取得.
	 * @return stockingAmount
	 */
	public java.math.BigDecimal getStockingAmount() {
		return this.stockingAmount;
	}

	/**
	 * stockingAmount設定.
	 * @param stockingAmount stockingAmount
	 */
	public void setStockingAmount(final java.math.BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * paymentAmount取得.
	 * @return paymentAmount
	 */
	public java.math.BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	/**
	 * paymentAmount設定.
	 * @param paymentAmount paymentAmount
	 */
	public void setPaymentAmount(final java.math.BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * otherPaymentAmount取得.
	 * @return otherPaymentAmount
	 */
	public java.math.BigDecimal getOtherPaymentAmount() {
		return this.otherPaymentAmount;
	}

	/**
	 * otherPaymentAmount設定.
	 * @param otherPaymentAmount otherPaymentAmount
	 */
	public void setOtherPaymentAmount(final java.math.BigDecimal otherPaymentAmount) {
		this.otherPaymentAmount = otherPaymentAmount;
	}

	/**
	 * stockingReturnedAmount取得.
	 * @return stockingReturnedAmount
	 */
	public java.math.BigDecimal getStockingReturnedAmount() {
		return this.stockingReturnedAmount;
	}

	/**
	 * stockingReturnedAmount設定.
	 * @param stockingReturnedAmount stockingReturnedAmount
	 */
	public void setStockingReturnedAmount(final java.math.BigDecimal stockingReturnedAmount) {
		this.stockingReturnedAmount = stockingReturnedAmount;
	}

	/**
	 * stockingDiscountAmount取得.
	 * @return stockingDiscountAmount
	 */
	public java.math.BigDecimal getStockingDiscountAmount() {
		return this.stockingDiscountAmount;
	}

	/**
	 * stockingDiscountAmount設定.
	 * @param stockingDiscountAmount stockingDiscountAmount
	 */
	public void setStockingDiscountAmount(final java.math.BigDecimal stockingDiscountAmount) {
		this.stockingDiscountAmount = stockingDiscountAmount;
	}

	/**
	 * otherStockingAmount取得.
	 * @return otherStockingAmount
	 */
	public java.math.BigDecimal getOtherStockingAmount() {
		return this.otherStockingAmount;
	}

	/**
	 * otherStockingAmount設定.
	 * @param otherStockingAmount otherStockingAmount
	 */
	public void setOtherStockingAmount(final java.math.BigDecimal otherStockingAmount) {
		this.otherStockingAmount = otherStockingAmount;
	}

	/**
	 * offsetAmount取得.
	 * @return offsetAmount
	 */
	public java.math.BigDecimal getOffsetAmount() {
		return this.offsetAmount;
	}

	/**
	 * offsetAmount設定.
	 * @param offsetAmount offsetAmount
	 */
	public void setOffsetAmount(final java.math.BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * taxAmount取得.
	 * @return taxAmount
	 */
	public java.math.BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * taxAmount設定.
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * payableAmount取得.
	 * @return payableAmount
	 */
	public java.math.BigDecimal getPayableAmount() {
		return this.payableAmount;
	}

	/**
	 * payableAmount設定.
	 * @param payableAmount payableAmount
	 */
	public void setPayableAmount(final java.math.BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}

	/**
	 * accountPayableBreakdown取得.
	 * @return accountPayableBreakdown
	 */
	public java.math.BigDecimal getAccountPayableBreakdown() {
		return this.accountPayableBreakdown;
	}

	/**
	 * accountPayableBreakdown設定.
	 * @param accountPayableBreakdown accountPayableBreakdown
	 */
	public void setAccountPayableBreakdown(final java.math.BigDecimal accountPayableBreakdown) {
		this.accountPayableBreakdown = accountPayableBreakdown;
	}

	/**
	 * arrearageBreakdown取得.
	 * @return arrearageBreakdown
	 */
	public java.math.BigDecimal getArrearageBreakdown() {
		return this.arrearageBreakdown;
	}

	/**
	 * arrearageBreakdown設定.
	 * @param arrearageBreakdown arrearageBreakdown
	 */
	public void setArrearageBreakdown(final java.math.BigDecimal arrearageBreakdown) {
		this.arrearageBreakdown = arrearageBreakdown;
	}

	/**
	 * exceptBreakdown取得.
	 * @return exceptBreakdown
	 */
	public java.math.BigDecimal getExceptBreakdown() {
		return this.exceptBreakdown;
	}

	/**
	 * exceptBreakdown設定.
	 * @param exceptBreakdown exceptBreakdown
	 */
	public void setExceptBreakdown(final java.math.BigDecimal exceptBreakdown) {
		this.exceptBreakdown = exceptBreakdown;
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
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * totalPayment取得.
	 * @return totalPayment
	 */
	public java.math.BigDecimal getTotalPayment() {
		return this.totalPayment;
	}

	/**
	 * totalPayment設定.
	 * @param totalPayment totalPayment
	 */
	public void setTotalPayment(final java.math.BigDecimal totalPayment) {
		this.totalPayment = totalPayment;
	}

	/**
	 * otherStocking取得.
	 * @return otherStocking
	 */
	public java.math.BigDecimal getOtherStocking() {
		return this.otherStocking;
	}

	/**
	 * otherStocking設定.
	 * @param otherStocking otherStocking
	 */
	public void setOtherStocking(final java.math.BigDecimal otherStocking) {
		this.otherStocking = otherStocking;
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

