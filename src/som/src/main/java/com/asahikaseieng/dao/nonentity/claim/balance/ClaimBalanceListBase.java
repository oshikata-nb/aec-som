/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.balance;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 請求残高一覧用Daoクラス.
 * @author tosco
 */
public class ClaimBalanceListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimBalanceListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション claimNo */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション claimAmountForward */
	public static final String claimAmountForward_COLUMN = "CLAIM_AMOUNT_FORWARD";

	/** COLUMNアノテーション salesAmount */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション creditAmountForward */
	public static final String creditAmountForward_COLUMN = "CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション otherCreditAmountForward */
	public static final String otherCreditAmountForward_COLUMN = "OTHER_CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション salesReturnedAmount */
	public static final String salesReturnedAmount_COLUMN = "SALES_RETURNED_AMOUNT";

	/** COLUMNアノテーション salesDiscountAmount */
	public static final String salesDiscountAmount_COLUMN = "SALES_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション otherSalesAmount */
	public static final String otherSalesAmount_COLUMN = "OTHER_SALES_AMOUNT";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション claimAmount */
	public static final String claimAmount_COLUMN = "CLAIM_AMOUNT";

	/** COLUMNアノテーション claimBalanceAmount. */
	public static final String claimBalanceAmount_COLUMN = "CLAIM_BALANCE_AMOUNT";

	/** COLUMNアノテーション otherCredit */
	public static final String otherCredit_COLUMN = "OTHER_CREDIT";

	/** COLUMNアノテーション otherSales */
	public static final String otherSales_COLUMN = "OTHER_SALES";

	/** COLUMNアノテーション creditLimitPrice */
	public static final String creditLimitPrice_COLUMN = "CREDIT_LIMIT_PRICE";

	//
	// インスタンスフィールド
	//

	/** 請求番号 */
	private String claimNo;

	/** 部門コード */
	private String organizationCd;

	/** 部門名称 */
	private String organizationName;

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	/** 請求先略称 */
	private String venderShortedName;

	/** 先月請求額（前回請求額） */
	private BigDecimal claimAmountForward;

	/** 売上金額（今回売上額） */
	private BigDecimal salesAmount;

	/** 前回入金額 */
	private BigDecimal creditAmountForward;

	/** その他入金金額 */
	private BigDecimal otherCreditAmountForward;

	/** 差額繰越 */
	private BigDecimal balanceForward;

	/** 返品金額 */
	private BigDecimal salesReturnedAmount;

	/** 値引金額 */
	private BigDecimal salesDiscountAmount;

	/** その他売上金額 */
	private BigDecimal otherSalesAmount;

	/** 相殺金額 */
	private BigDecimal offsetAmount;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 請求残高 */
	private BigDecimal claimAmount;

	private java.math.BigDecimal claimBalanceAmount;

	/** 入金・その他計 */
	private BigDecimal otherCredit;

	/** その他計 */
	private BigDecimal otherSales;

	/** 与信限度額 */
	private BigDecimal creditLimitPrice;

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
	 * venderNameを取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * venderNameを設定します。
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
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * otherCreditを取得します。
	 * @return otherCredit
	 */
	public BigDecimal getOtherCredit() {
		return otherCredit;
	}

	/**
	 * otherCreditを設定します。
	 * @param otherCredit otherCredit
	 */
	public void setOtherCredit(final BigDecimal otherCredit) {
		this.otherCredit = otherCredit;
	}

	/**
	 * otherSalesを取得します。
	 * @return otherSales
	 */
	public BigDecimal getOtherSales() {
		return otherSales;
	}

	/**
	 * otherSalesを設定します。
	 * @param otherSales otherSales
	 */
	public void setOtherSales(final BigDecimal otherSales) {
		this.otherSales = otherSales;
	}

	/**
	 * claimNoを取得します。
	 * @return claimNo
	 */
	public String getClaimNo() {
		return claimNo;
	}

	/**
	 * claimNoを設定します。
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
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
	 * claimAmountを取得します。
	 * @return claimAmount
	 */
	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	/**
	 * claimAmountを設定します。
	 * @param claimAmount claimAmount
	 */
	public void setClaimAmount(final BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * claimAmountForwardを取得します。
	 * @return claimAmountForward
	 */
	public BigDecimal getClaimAmountForward() {
		return claimAmountForward;
	}

	/**
	 * claimBalanceAmount取得.
	 * @return claimBalanceAmount
	 */
	public java.math.BigDecimal getClaimBalanceAmount() {
		return this.claimBalanceAmount;
	}

	/**
	 * claimBalanceAmount設定.
	 * @param claimBalanceAmount claimBalanceAmount
	 */
	public void setClaimBalanceAmount(
			final java.math.BigDecimal claimBalanceAmount) {
		this.claimBalanceAmount = claimBalanceAmount;
	}

	/**
	 * claimAmountForwardを設定します。
	 * @param claimAmountForward claimAmountForward
	 */
	public void setClaimAmountForward(final BigDecimal claimAmountForward) {
		this.claimAmountForward = claimAmountForward;
	}

	/**
	 * salesAmountを取得します。
	 * @return salesAmount
	 */
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	/**
	 * salesAmountを設定します。
	 * @param salesAmount salesAmount
	 */
	public void setSalesAmount(final BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * creditLimitPriceを取得します。
	 * @return creditLimitPrice
	 */
	public BigDecimal getCreditLimitPrice() {
		return creditLimitPrice;
	}

	/**
	 * creditLimitPriceを設定します。
	 * @param creditLimitPrice creditLimitPrice
	 */
	public void setCreditLimitPrice(final BigDecimal creditLimitPrice) {
		this.creditLimitPrice = creditLimitPrice;
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
	 * creditAmountForwardを取得します。
	 * @return creditAmountForward
	 */
	public BigDecimal getCreditAmountForward() {
		return creditAmountForward;
	}

	/**
	 * creditAmountForwardを設定します。
	 * @param creditAmountForward creditAmountForward
	 */
	public void setCreditAmountForward(final BigDecimal creditAmountForward) {
		this.creditAmountForward = creditAmountForward;
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
	 * otherCreditAmountForwardを取得します。
	 * @return otherCreditAmountForward
	 */
	public BigDecimal getOtherCreditAmountForward() {
		return otherCreditAmountForward;
	}

	/**
	 * otherCreditAmountForwardを設定します。
	 * @param otherCreditAmountForward otherCreditAmountForward
	 */
	public void setOtherCreditAmountForward(
			final BigDecimal otherCreditAmountForward) {
		this.otherCreditAmountForward = otherCreditAmountForward;
	}

	/**
	 * otherSalesAmountを取得します。
	 * @return otherSalesAmount
	 */
	public BigDecimal getOtherSalesAmount() {
		return otherSalesAmount;
	}

	/**
	 * otherSalesAmountを設定します。
	 * @param otherSalesAmount otherSalesAmount
	 */
	public void setOtherSalesAmount(final BigDecimal otherSalesAmount) {
		this.otherSalesAmount = otherSalesAmount;
	}

	/**
	 * salesDiscountAmountを取得します。
	 * @return salesDiscountAmount
	 */
	public BigDecimal getSalesDiscountAmount() {
		return salesDiscountAmount;
	}

	/**
	 * salesDiscountAmountを設定します。
	 * @param salesDiscountAmount salesDiscountAmount
	 */
	public void setSalesDiscountAmount(final BigDecimal salesDiscountAmount) {
		this.salesDiscountAmount = salesDiscountAmount;
	}

	/**
	 * salesReturnedAmountを取得します。
	 * @return salesReturnedAmount
	 */
	public BigDecimal getSalesReturnedAmount() {
		return salesReturnedAmount;
	}

	/**
	 * salesReturnedAmountを設定します。
	 * @param salesReturnedAmount salesReturnedAmount
	 */
	public void setSalesReturnedAmount(final BigDecimal salesReturnedAmount) {
		this.salesReturnedAmount = salesReturnedAmount;
	}

}
