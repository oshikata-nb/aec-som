/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalance;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ArBalanceListクラス.
 * @author t0011036
 */
public class ArBalanceListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArBalanceListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション depositNo */
	public static final String depositNo_COLUMN = "DEPOSIT_NO";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション sectionName */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション salesAmount */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション depositAmount */
	public static final String depositAmount_COLUMN = "DEPOSIT_AMOUNT";

	/** COLUMNアノテーション otherDepositAmount */
	public static final String otherDepositAmount_COLUMN = "OTHER_DEPOSIT_AMOUNT";

	/** COLUMNアノテーション returnedAmount */
	public static final String returnedAmount_COLUMN = "RETURNED_AMOUNT";

	/** COLUMNアノテーション discountAmount */
	public static final String discountAmount_COLUMN = "DISCOUNT_AMOUNT";

	/** COLUMNアノテーション otherSalesAmount */
	public static final String otherSalesAmount_COLUMN = "OTHER_SALES_AMOUNT";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション creditSalesBreakdown */
	public static final String creditSalesBreakdown_COLUMN = "CREDIT_SALES_BREAKDOWN";

	/** COLUMNアノテーション accruedDebitBreakdown */
	public static final String accruedDebitBreakdown_COLUMN = "ACCRUED_DEBIT_BREAKDOWN";

	/** COLUMNアノテーション creditAmount */
	public static final String creditAmount_COLUMN = "CREDIT_AMOUNT";

	/** COLUMNアノテーション claimBalance */
	public static final String claimBalance_COLUMN = "CLAIM_BALANCE";

	/** COLUMNアノテーション claimForward */
	public static final String claimForward_COLUMN = "CLAIM_FORWARD";

	/** COLUMNアノテーション otherDeposit */
	public static final String otherDeposit_COLUMN = "OTHER_DEPOSIT";

	/** COLUMNアノテーション otherSales */
	public static final String otherSales_COLUMN = "OTHER_SALES";

	/** COLUMNアノテーション closingMonth */
	public static final String closingMonth_COLUMN = "CLOSING_MONTH";

	//
	// インスタンスフィールド
	//

	private String depositNo;

	private String sectionCd;

	private String sectionName;

	private String venderCd;

	private String venderName;

	private String venderShortedName;

	private java.math.BigDecimal balanceForward;

	private java.math.BigDecimal salesAmount;

	private java.math.BigDecimal depositAmount;

	private java.math.BigDecimal otherDepositAmount;

	private java.math.BigDecimal returnedAmount;

	private java.math.BigDecimal discountAmount;

	private java.math.BigDecimal otherSalesAmount;

	private java.math.BigDecimal offsetAmount;

	private java.math.BigDecimal taxAmount;

	private java.math.BigDecimal creditSalesBreakdown;

	private java.math.BigDecimal accruedDebitBreakdown;

	private java.math.BigDecimal creditAmount;

	private java.math.BigDecimal claimBalance;

	private java.math.BigDecimal claimForward;

	private java.math.BigDecimal otherDeposit;

	private java.math.BigDecimal otherSales;

	private String closingMonth;

	//
	// インスタンスメソッド
	//

	/**
	 * depositNo取得.
	 * @return depositNo
	 */
	public String getDepositNo() {
		return this.depositNo;
	}

	/**
	 * depositNo設定.
	 * @param depositNo depositNo
	 */
	public void setDepositNo(final String depositNo) {
		this.depositNo = depositNo;
	}

	/**
	 * sectionCd取得.
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return this.sectionCd;
	}

	/**
	 * sectionCd設定.
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * sectionName取得.
	 * @return sectionName
	 */
	public String getSectionName() {
		return this.sectionName;
	}

	/**
	 * sectionName設定.
	 * @param sectionName sectionName
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
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
	 * salesAmount取得.
	 * @return salesAmount
	 */
	public java.math.BigDecimal getSalesAmount() {
		return this.salesAmount;
	}

	/**
	 * salesAmount設定.
	 * @param salesAmount salesAmount
	 */
	public void setSalesAmount(final java.math.BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * depositAmount取得.
	 * @return depositAmount
	 */
	public java.math.BigDecimal getDepositAmount() {
		return this.depositAmount;
	}

	/**
	 * depositAmount設定.
	 * @param depositAmount depositAmount
	 */
	public void setDepositAmount(final java.math.BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	/**
	 * otherDepositAmount取得.
	 * @return otherDepositAmount
	 */
	public java.math.BigDecimal getOtherDepositAmount() {
		return this.otherDepositAmount;
	}

	/**
	 * otherDepositAmount設定.
	 * @param otherDepositAmount otherDepositAmount
	 */
	public void setOtherDepositAmount(
			final java.math.BigDecimal otherDepositAmount) {
		this.otherDepositAmount = otherDepositAmount;
	}

	/**
	 * returnedAmount取得.
	 * @return returnedAmount
	 */
	public java.math.BigDecimal getReturnedAmount() {
		return this.returnedAmount;
	}

	/**
	 * returnedAmount設定.
	 * @param returnedAmount returnedAmount
	 */
	public void setReturnedAmount(final java.math.BigDecimal returnedAmount) {
		this.returnedAmount = returnedAmount;
	}

	/**
	 * discountAmount取得.
	 * @return discountAmount
	 */
	public java.math.BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	/**
	 * discountAmount設定.
	 * @param discountAmount discountAmount
	 */
	public void setDiscountAmount(final java.math.BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * otherSalesAmount取得.
	 * @return otherSalesAmount
	 */
	public java.math.BigDecimal getOtherSalesAmount() {
		return this.otherSalesAmount;
	}

	/**
	 * otherSalesAmount設定.
	 * @param otherSalesAmount otherSalesAmount
	 */
	public void setOtherSalesAmount(final java.math.BigDecimal otherSalesAmount) {
		this.otherSalesAmount = otherSalesAmount;
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
	 * creditSalesBreakdown取得.
	 * @return creditSalesBreakdown
	 */
	public java.math.BigDecimal getCreditSalesBreakdown() {
		return this.creditSalesBreakdown;
	}

	/**
	 * creditSalesBreakdown設定.
	 * @param creditSalesBreakdown creditSalesBreakdown
	 */
	public void setCreditSalesBreakdown(
			final java.math.BigDecimal creditSalesBreakdown) {
		this.creditSalesBreakdown = creditSalesBreakdown;
	}

	/**
	 * accruedDebitBreakdown取得.
	 * @return accruedDebitBreakdown
	 */
	public java.math.BigDecimal getAccruedDebitBreakdown() {
		return this.accruedDebitBreakdown;
	}

	/**
	 * accruedDebitBreakdown設定.
	 * @param accruedDebitBreakdown accruedDebitBreakdown
	 */
	public void setAccruedDebitBreakdown(
			final java.math.BigDecimal accruedDebitBreakdown) {
		this.accruedDebitBreakdown = accruedDebitBreakdown;
	}

	/**
	 * creditAmount取得.
	 * @return creditAmount
	 */
	public java.math.BigDecimal getCreditAmount() {
		return this.creditAmount;
	}

	/**
	 * creditAmount設定.
	 * @param creditAmount creditAmount
	 */
	public void setCreditAmount(final java.math.BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * claimBalance取得.
	 * @return claimBalance
	 */
	public java.math.BigDecimal getClaimBalance() {
		return this.claimBalance;
	}

	/**
	 * claimBalance設定.
	 * @param claimBalance claimBalance
	 */
	public void setClaimBalance(final java.math.BigDecimal claimBalance) {
		this.claimBalance = claimBalance;
	}

	/**
	 * claimForward取得.
	 * @return claimForward
	 */
	public java.math.BigDecimal getClaimForward() {
		return this.claimForward;
	}

	/**
	 * claimForward設定.
	 * @param claimForward claimForward
	 */
	public void setClaimForward(final java.math.BigDecimal claimForward) {
		this.claimForward = claimForward;
	}

	/**
	 * otherDeposit取得.
	 * @return otherDeposit
	 */
	public java.math.BigDecimal getOtherDeposit() {
		return this.otherDeposit;
	}

	/**
	 * otherDeposit設定.
	 * @param otherDeposit otherDeposit
	 */
	public void setOtherDeposit(final java.math.BigDecimal otherDeposit) {
		this.otherDeposit = otherDeposit;
	}

	/**
	 * otherSales取得.
	 * @return otherSales
	 */
	public java.math.BigDecimal getOtherSales() {
		return this.otherSales;
	}

	/**
	 * otherSales設定.
	 * @param otherSales otherSales
	 */
	public void setOtherSales(final java.math.BigDecimal otherSales) {
		this.otherSales = otherSales;
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
	 * closingMonthを取得します。
	 * @return closingMonth
	 */
	public String getClosingMonth() {
		return closingMonth;
	}

	/**
	 * closingMonthを設定します。
	 * @param closingMonth closingMonth
	 */
	public void setClosingMonth(final String closingMonth) {
		this.closingMonth = closingMonth;
	}
}
