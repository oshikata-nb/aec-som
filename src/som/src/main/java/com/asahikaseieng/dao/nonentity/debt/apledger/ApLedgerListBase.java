/*
 * Created on 2008/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledger;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 買掛元帳用Daoクラス.
 * @author tosco
 */
public class ApLedgerListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApLedgerListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション payableNo */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション paymentAmount */
	public static final String paymentAmount_COLUMN = "PAYMENT_AMOUNT";

	/** COLUMNアノテーション otherPaymentAmount */
	public static final String otherPaymentAmount_COLUMN = "OTHER_PAYMENT_AMOUNT";

	/** COLUMNアノテーション discountAmount */
	public static final String discountAmount_COLUMN = "STOCKING_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション returnedAmount */
	public static final String returnedAmount_COLUMN = "STOCKING_RETURNED_AMOUNT";

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

	/** COLUMNアノテーション otherPayment */
	public static final String otherPayment_COLUMN = "OTHER_PAYMENT";

	/** COLUMNアノテーション otherStocking */
	public static final String otherStocking_COLUMN = "OTHER_STOCKING";

	//
	// インスタンスフィールド
	//

	/** 買掛番号 */
	private String payableNo;

	/** 支払先コード */
	private String venderCd;

	/** 支払先名称 */
	private String venderName1;

	/** 支払先略称 */
	private String venderShortedName;

	/** 前月買掛残 */
	private BigDecimal balanceForward;

	/** 支払金額 */
	private BigDecimal paymentAmount;

	/** その他支払金額 */
	private BigDecimal otherPaymentAmount;

	/** 仕入金額 */
	private BigDecimal stockingAmount;

	/** 値引金額 */
	private BigDecimal discountAmount;

	/** 返品金額 */
	private BigDecimal returnedAmount;

	/** その他仕入金額 */
	private BigDecimal otherStockingAmount;

	/** 相殺金額 */
	private BigDecimal offsetAmount;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 買掛金(内訳) */
	private BigDecimal accountPayableBreakdouwn;

	/** 未払金(内訳) */
	private BigDecimal arrearageBreakdouwn;

	/** 買掛残高 */
	private BigDecimal payableAmount;

	/** 支払・その他計（支払金額＋その他支払金額） */
	private BigDecimal otherPayment;

	/** その他計（返品金額＋値引金額＋その他仕入金額＋相殺金額） */
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
	 * 買掛番号を取得します。
	 * @return payableNo
	 */
	public String getPayableNo() {
		return payableNo;
	}

	/**
	 * 買掛番号を設定します。
	 * @param payableNo 買掛番号
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * 支払先コードを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 支払先コードを設定します。
	 * @param venderCd 支払先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 支払先名称を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 支払先名称を設定します。
	 * @param venderName1 支払先名称
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
	 * 前月買掛残を取得します。
	 * @return balanceForward
	 */
	public BigDecimal getBalanceForward() {
		return balanceForward;
	}

	/**
	 * 前月買掛残を設定します。
	 * @param balanceForward 前月買掛残
	 */
	public void setBalanceForward(final BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * 支払金額を取得します。
	 * @return paymentAmount
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * 支払金額を設定します。
	 * @param paymentAmount 支払金額
	 */
	public void setPaymentAmount(final BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * その他支払金額を取得します。
	 * @return otherPaymentAmount
	 */
	public BigDecimal getOtherPaymentAmount() {
		return otherPaymentAmount;
	}

	/**
	 * その他支払金額を設定します。
	 * @param otherPaymentAmount その他支払金額
	 */
	public void setOtherPaymentAmount(final BigDecimal otherPaymentAmount) {
		this.otherPaymentAmount = otherPaymentAmount;
	}

	/**
	 * 仕入金額を取得します。
	 * @return stockingAmount
	 */
	public BigDecimal getStockingAmount() {
		return stockingAmount;
	}

	/**
	 * 仕入金額を設定します。
	 * @param stockingAmount 仕入金額
	 */
	public void setStockingAmount(final BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * 値引金額を取得します。
	 * @return discountAmount
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * 値引金額を設定します。
	 * @param discountAmount 値引金額
	 */
	public void setDiscountAmount(final BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * 返品金額を取得します。
	 * @return returnedAmount
	 */
	public BigDecimal getReturnedAmount() {
		return returnedAmount;
	}

	/**
	 * 返品金額を設定します。
	 * @param returnedAmount 返品金額
	 */
	public void setReturnedAmount(final BigDecimal returnedAmount) {
		this.returnedAmount = returnedAmount;
	}

	/**
	 * その他仕入金額を取得します。
	 * @return otherStockingAmount
	 */
	public BigDecimal getOtherStockingAmount() {
		return otherStockingAmount;
	}

	/**
	 * その他仕入金額を設定します。
	 * @param otherStockingAmount その他仕入金額
	 */
	public void setOtherStockingAmount(final BigDecimal otherStockingAmount) {
		this.otherStockingAmount = otherStockingAmount;
	}

	/**
	 * 相殺金額を取得します。
	 * @return offsetAmount
	 */
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * 相殺金額を設定します。
	 * @param offsetAmount 相殺金額
	 */
	public void setOffsetAmount(final BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * 消費税を取得します。
	 * @return taxAmount
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	/**
	 * 消費税を設定します。
	 * @param taxAmount 消費税
	 */
	public void setTaxAmount(final BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * 買掛残高を取得します。
	 * @return payableAmount
	 */
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}

	/**
	 * 買掛残高を設定します。
	 * @param payableAmount 買掛残高
	 */
	public void setPayableAmount(final BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}

	/**
	 * 支払・その他計を取得します。
	 * @return otherPayment
	 */
	public BigDecimal getOtherPayment() {
		return otherPayment;
	}

	/**
	 * 支払・その他計を設定します。
	 * @param otherPayment 支払・その他計
	 */
	public void setOtherPayment(final BigDecimal otherPayment) {
		this.otherPayment = otherPayment;
	}

	/**
	 * その他計を取得します。
	 * @return otherStocking
	 */
	public BigDecimal getOtherStocking() {
		return otherStocking;
	}

	/**
	 * その他計を設定します。
	 * @param otherStocking その他計
	 */
	public void setOtherStocking(final BigDecimal otherStocking) {
		this.otherStocking = otherStocking;
	}

	/**
	 * 買掛金(内訳)を取得します。
	 * @return accountPayableBreakdouwn
	 */
	public BigDecimal getAccountPayableBreakdouwn() {
		return accountPayableBreakdouwn;
	}

	/**
	 * 買掛金(内訳)を設定します。
	 * @param accountPayableBreakdouwn 買掛金(内訳)
	 */
	public void setAccountPayableBreakdouwn(
			final BigDecimal accountPayableBreakdouwn) {
		this.accountPayableBreakdouwn = accountPayableBreakdouwn;
	}

	/**
	 * 未払金(内訳)を取得します。
	 * @return arrearageBreakdouwn
	 */
	public BigDecimal getArrearageBreakdouwn() {
		return arrearageBreakdouwn;
	}

	/**
	 * 未払金(内訳)を設定します。
	 * @param arrearageBreakdouwn 未払金(内訳)
	 */
	public void setArrearageBreakdouwn(final BigDecimal arrearageBreakdouwn) {
		this.arrearageBreakdouwn = arrearageBreakdouwn;
	}

}
