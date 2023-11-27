/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apbalance;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 買掛残高一覧用Daoクラス.
 * @author tosco
 */
public class ApBalanceListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApBalanceListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション payableNo */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

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

	/** COLUMNアノテーション stockingReturned */
	public static final String stockingReturned_COLUMN = "STOCKING_RETURNED_AMOUNT";

	/** COLUMNアノテーション stockingDiscount */
	public static final String stockingDiscount_COLUMN = "STOCKING_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション otherStockingAmount */
	public static final String otherStockingAmount_COLUMN = "OTHER_STOCKING_AMOUNT";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション accountPayableBreakdouwn */
	public static final String accountPayableBreakdouwn_COLUMN = "ACCOUNT_PAYABLE_BREAKDOWN";

	/** COLUMNアノテーション arrearageBreakdouwn */
	public static final String arrearageBreakdouwn_COLUMN = "ARREARAGE_BREAKDOWN";

	/** COLUMNアノテーション payableAmount */
	public static final String payableAmount_COLUMN = "PAYABLE_AMOUNT";

	/** COLUMNアノテーション totalPayment */
	public static final String totalPayment_COLUMN = "TOTAL_PAYMENT";

	/** COLUMNアノテーション otherStocking */
	public static final String otherStocking_COLUMN = "OTHER_STOCKING";

	//
	// インスタンスフィールド
	//

	/** 買掛番号 */
	private String payableNo;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 支払先コード */
	private String venderCd;

	/** 支払先名称1 */
	private String venderName1;

	/** 支払先名称1 */
	private String venderShortedName;

	/** 買掛締め日 */
	private String payableDate;

	/** 前月繰越 */
	private BigDecimal balanceForward;

	/** 仕入金額 */
	private BigDecimal stockingAmount;

	/** 支払金額 */
	private BigDecimal paymentAmount;

	/** その他支払金額 */
	private BigDecimal otherPaymentAmount;

	/** 返品金額 */
	private BigDecimal stockingReturned;

	/** 値引金額 */
	private BigDecimal stockingDiscount;

	/** その他仕入金額 */
	private BigDecimal otherStockingAmount;

	/** 相殺 */
	private BigDecimal offsetAmount;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 買掛金(内訳) */
	private BigDecimal accountPayableBreakdouwn;

	/** 未払金(内訳) */
	private BigDecimal arrearageBreakdouwn;

	/** 買掛残高 */
	private BigDecimal payableAmount;

	/** 支払合計(支払金額＋その他支払金額) */
	private BigDecimal totalPayment;

	/** その他計(返品金額＋値引金額＋その他仕入金額＋相殺) */
	private BigDecimal otherStocking;

	//
	// インスタンスメソッド
	//

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
	 * balanceForwardを取得します。
	 * @return balanceForward
	 */
	public BigDecimal getBalanceForward() {
		return balanceForward;
	}

	/**
	 * balanceForwardを設定します。
	 * @param balanceForward balanceForward
	 */
	public void setBalanceForward(final BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * offsetAmountを取得します。
	 * @return offsetAmount
	 */
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * offsetAmountを設定します。
	 * @param offsetAmount offsetAmount
	 */
	public void setOffsetAmount(final BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * otherPaymentAmountを取得します。
	 * @return otherPaymentAmount
	 */
	public BigDecimal getOtherPaymentAmount() {
		return otherPaymentAmount;
	}

	/**
	 * otherPaymentAmountを設定します。
	 * @param otherPaymentAmount otherPaymentAmount
	 */
	public void setOtherPaymentAmount(final BigDecimal otherPaymentAmount) {
		this.otherPaymentAmount = otherPaymentAmount;
	}

	/**
	 * otherStockingを取得します。
	 * @return otherStocking
	 */
	public BigDecimal getOtherStocking() {
		return otherStocking;
	}

	/**
	 * otherStockingを設定します。
	 * @param otherStocking otherStocking
	 */
	public void setOtherStocking(final BigDecimal otherStocking) {
		this.otherStocking = otherStocking;
	}

	/**
	 * otherStockingAmountを取得します。
	 * @return otherStockingAmount
	 */
	public BigDecimal getOtherStockingAmount() {
		return otherStockingAmount;
	}

	/**
	 * otherStockingAmountを設定します。
	 * @param otherStockingAmount otherStockingAmount
	 */
	public void setOtherStockingAmount(final BigDecimal otherStockingAmount) {
		this.otherStockingAmount = otherStockingAmount;
	}

	/**
	 * payableAmountを取得します。
	 * @return payableAmount
	 */
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}

	/**
	 * payableAmountを設定します。
	 * @param payableAmount payableAmount
	 */
	public void setPayableAmount(final BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}

	/**
	 * payableDateを取得します。
	 * @return payableDate
	 */
	public String getPayableDate() {
		return payableDate;
	}

	/**
	 * payableDateを設定します。
	 * @param payableDate payableDate
	 */
	public void setPayableDate(final String payableDate) {
		this.payableDate = payableDate;
	}

	/**
	 * payableNoを取得します。
	 * @return payableNo
	 */
	public String getPayableNo() {
		return payableNo;
	}

	/**
	 * payableNoを設定します。
	 * @param payableNo payableNo
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * paymentAmountを取得します。
	 * @return paymentAmount
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * paymentAmountを設定します。
	 * @param paymentAmount paymentAmount
	 */
	public void setPaymentAmount(final BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
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
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getSectionName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setSectionName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * stockingAmountを取得します。
	 * @return stockingAmount
	 */
	public BigDecimal getStockingAmount() {
		return stockingAmount;
	}

	/**
	 * stockingAmountを設定します。
	 * @param stockingAmount stockingAmount
	 */
	public void setStockingAmount(final BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * stockingDiscountを取得します。
	 * @return stockingDiscount
	 */
	public BigDecimal getStockingDiscount() {
		return stockingDiscount;
	}

	/**
	 * stockingDiscountを設定します。
	 * @param stockingDiscount stockingDiscount
	 */
	public void setStockingDiscount(final BigDecimal stockingDiscount) {
		this.stockingDiscount = stockingDiscount;
	}

	/**
	 * stockingReturnedを取得します。
	 * @return stockingReturned
	 */
	public BigDecimal getStockingReturned() {
		return stockingReturned;
	}

	/**
	 * stockingReturnedを設定します。
	 * @param stockingReturned stockingReturned
	 */
	public void setStockingReturned(final BigDecimal stockingReturned) {
		this.stockingReturned = stockingReturned;
	}

	/**
	 * taxAmountを取得します。
	 * @return taxAmount
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	/**
	 * taxAmountを設定します。
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
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
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * totalPaymentを取得します。
	 * @return totalPayment
	 */
	public BigDecimal getTotalPayment() {
		return totalPayment;
	}

	/**
	 * totalPaymentを設定します。
	 * @param totalPayment totalPayment
	 */
	public void setTotalPayment(final BigDecimal totalPayment) {
		this.totalPayment = totalPayment;
	}

	/**
	 * accountPayableBreakdouwnを取得します。
	 * @return accountPayableBreakdouwn
	 */
	public BigDecimal getAccountPayableBreakdouwn() {
		return accountPayableBreakdouwn;
	}

	/**
	 * accountPayableBreakdouwnを設定します。
	 * @param accountPayableBreakdouwn accountPayableBreakdouwn
	 */
	public void setAccountPayableBreakdouwn(
			final BigDecimal accountPayableBreakdouwn) {
		this.accountPayableBreakdouwn = accountPayableBreakdouwn;
	}

	/**
	 * arrearageBreakdouwnを取得します。
	 * @return arrearageBreakdouwn
	 */
	public BigDecimal getArrearageBreakdouwn() {
		return arrearageBreakdouwn;
	}

	/**
	 * arrearageBreakdouwnを設定します。
	 * @param arrearageBreakdouwn arrearageBreakdouwn
	 */
	public void setArrearageBreakdouwn(final BigDecimal arrearageBreakdouwn) {
		this.arrearageBreakdouwn = arrearageBreakdouwn;
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
}
