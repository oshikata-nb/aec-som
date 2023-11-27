/*
 * Created on 2009/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender.payment;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PaymentVenderForAutoCompleteクラス.
 * @author t0011036
 */
public class PaymentVenderForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PaymentVenderForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション paymentScheduledDate */
	public static final String paymentScheduledDate_COLUMN = "PAYMENT_SCHEDULED_DATE";

	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション accountPayableSum */
	public static final String accountPayableSum_COLUMN = "ACCOUNT_PAYABLE_SUM";

	/** COLUMNアノテーション payableAmount */
	public static final String payableAmount_COLUMN = "PAYABLE_AMOUNT";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション commission */
	public static final String commission_COLUMN = "COMMISSION";

	/** COLUMNアノテーション purchaseDiscountAmount */
	public static final String purchaseDiscountAmount_COLUMN = "PURCHASE_DISCOUNT_AMOUNT";

	//
	// インスタンスフィールド
	//

	private String venderCd;

	private String venderShortedName;

	private String categoryDivision;

	private String categoryName;

	private java.sql.Timestamp paymentScheduledDate;

	private java.math.BigDecimal balanceForward;

	private java.math.BigDecimal accountPayableSum;

	private java.math.BigDecimal payableAmount;

	private java.math.BigDecimal offsetAmount;

	private java.math.BigDecimal commission;

	private java.math.BigDecimal purchaseDiscountAmount;

	//
	// インスタンスメソッド
	//

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
	 * venderShortedName取得.
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return this.venderShortedName;
	}

	/**
	 * venderShortedName設定.
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * categoryDivision取得.
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * categoryDivision設定.
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * categoryName取得.
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * categoryName設定.
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * paymentScheduledDate取得.
	 * @return paymentScheduledDate
	 */
	public java.sql.Timestamp getPaymentScheduledDate() {
		return this.paymentScheduledDate;
	}

	/**
	 * paymentScheduledDate設定.
	 * @param paymentScheduledDate paymentScheduledDate
	 */
	public void setPaymentScheduledDate(final java.sql.Timestamp paymentScheduledDate) {
		this.paymentScheduledDate = paymentScheduledDate;
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
	 * accountPayableSum取得.
	 * @return accountPayableSum
	 */
	public java.math.BigDecimal getAccountPayableSum() {
		return this.accountPayableSum;
	}

	/**
	 * accountPayableSum設定.
	 * @param accountPayableSum accountPayableSum
	 */
	public void setAccountPayableSum(final java.math.BigDecimal accountPayableSum) {
		this.accountPayableSum = accountPayableSum;
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
	 * commission取得.
	 * @return commission
	 */
	public java.math.BigDecimal getCommission() {
		return this.commission;
	}

	/**
	 * commission設定.
	 * @param commission commission
	 */
	public void setCommission(final java.math.BigDecimal commission) {
		this.commission = commission;
	}

	/**
	 * purchaseDiscountAmount取得.
	 * @return purchaseDiscountAmount
	 */
	public java.math.BigDecimal getPurchaseDiscountAmount() {
		return this.purchaseDiscountAmount;
	}

	/**
	 * purchaseDiscountAmount設定.
	 * @param purchaseDiscountAmount purchaseDiscountAmount
	 */
	public void setPurchaseDiscountAmount(final java.math.BigDecimal purchaseDiscountAmount) {
		this.purchaseDiscountAmount = purchaseDiscountAmount;
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

