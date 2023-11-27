/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledger;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売掛元帳用クラス.
 * @author tosco
 */
public class ArLedgerListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArLedgerListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション depositNo */
	public static final String depositNo_COLUMN = "DEPOSIT_NO";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "CUSTOMER_CD";

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

	/** COLUMNアノテーション otherDeposit */
	public static final String otherDeposit_COLUMN = "OTHER_DEPOSIT";

	/** COLUMNアノテーション otherSales */
	public static final String otherSales_COLUMN = "OTHER_SALES";

	//
	// インスタンスフィールド
	//

	/** 売掛番号 */
	private String depositNo;

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	/** 請求先略称 */
	private String venderShortedName;

	/** 先月繰越 */
	private BigDecimal balanceForward;

	/** 売上金額 */
	private BigDecimal salesAmount;

	/** 入金金額 */
	private BigDecimal depositAmount;

	/** その他入金金額 */
	private BigDecimal otherDepositAmount;

	/** 返品金額 */
	private BigDecimal returnedAmount;

	/** 値引金額 */
	private BigDecimal discountAmount;

	/** その他売上金額 */
	private BigDecimal otherSalesAmount;

	/** 相殺金額 */
	private BigDecimal offsetAmount;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 売掛残(内訳) */
	private BigDecimal creditSalesBreakdown;

	/** 未収金(内訳) */
	private BigDecimal accruedDebitBreakdown;

	/** 当月残 */
	private BigDecimal creditAmount;

	/** 入金・その他計（入金金額＋その他入金金額） */
	private BigDecimal otherDeposit;

	/** その他計（返品金額＋値引金額＋その他売上金額＋相殺金額） */
	private BigDecimal otherSales;

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
	 * 売掛番号を取得します。
	 * @return depositNo
	 */
	public String getDepositNo() {
		return depositNo;
	}

	/**
	 * 売掛番号を設定します。
	 * @param depositNo 売掛番号
	 */
	public void setDepositNo(final String depositNo) {
		this.depositNo = depositNo;
	}

	/**
	 * 請求先コードを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 請求先コードを設定します。
	 * @param venderCd 請求先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 請求先名称を取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 請求先名称を設定します。
	 * @param venderName 請求先名称
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
	 * 先月繰越を取得します。
	 * @return balanceForward
	 */
	public BigDecimal getBalanceForward() {
		return balanceForward;
	}

	/**
	 * 先月繰越を設定します。
	 * @param balanceForward 先月繰越
	 */
	public void setBalanceForward(final BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * 売上金額を取得します。
	 * @return salesAmount
	 */
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	/**
	 * 売上金額を設定します。
	 * @param salesAmount 売上金額
	 */
	public void setSalesAmount(final BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * 入金金額を取得します。
	 * @return depositAmount
	 */
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	/**
	 * 入金金額を設定します。
	 * @param depositAmount 入金金額
	 */
	public void setDepositAmount(final BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	/**
	 * その他入金金額を取得します。
	 * @return otherDepositAmount
	 */
	public BigDecimal getOtherDepositAmount() {
		return otherDepositAmount;
	}

	/**
	 * その他入金金額を設定します。
	 * @param otherDepositAmount その他入金金額
	 */
	public void setOtherDepositAmount(final BigDecimal otherDepositAmount) {
		this.otherDepositAmount = otherDepositAmount;
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
	 * その他売上金額を取得します。
	 * @return otherSalesAmount
	 */
	public BigDecimal getOtherSalesAmount() {
		return otherSalesAmount;
	}

	/**
	 * その他売上金額を設定します。
	 * @param otherSalesAmount その他売上金額
	 */
	public void setOtherSalesAmount(final BigDecimal otherSalesAmount) {
		this.otherSalesAmount = otherSalesAmount;
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
	 * 未収金(内訳)を取得します。
	 * @return accruedDebitBreakdown
	 */
	public BigDecimal getAccruedDebitBreakdown() {
		return accruedDebitBreakdown;
	}

	/**
	 * 未収金(内訳)を設定します。
	 * @param accruedDebitBreakdown 未収金(内訳)
	 */
	public void setAccruedDebitBreakdown(final BigDecimal accruedDebitBreakdown) {
		this.accruedDebitBreakdown = accruedDebitBreakdown;
	}

	/**
	 * 売掛残(内訳)を取得します。
	 * @return creditSalesBreakdown
	 */
	public BigDecimal getCreditSalesBreakdown() {
		return creditSalesBreakdown;
	}

	/**
	 * 売掛残(内訳)を設定します。
	 * @param creditSalesBreakdown 売掛残(内訳)
	 */
	public void setCreditSalesBreakdown(final BigDecimal creditSalesBreakdown) {
		this.creditSalesBreakdown = creditSalesBreakdown;
	}

	/**
	 * 売掛残高を取得します。
	 * @return creditAmount
	 */
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	/**
	 * 売掛残高を設定します。
	 * @param creditAmount 売掛残高
	 */
	public void setCreditAmount(final BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * 入金・その他計（入金金額＋その他入金金額）を取得します。
	 * @return otherDeposit
	 */
	public java.math.BigDecimal getOtherDeposit() {
		return otherDeposit;
	}

	/**
	 * 入金・その他計（入金金額＋その他入金金額）を設定します。
	 * @param otherDeposit 入金・その他計（入金金額＋その他入金金額）
	 */
	public void setOtherDeposit(final java.math.BigDecimal otherDeposit) {
		this.otherDeposit = otherDeposit;
	}

	/**
	 * その他計（返品金額＋値引金額＋その他売上金額＋相殺金額）を取得します。
	 * @return otherSales
	 */
	public java.math.BigDecimal getOtherSales() {
		return otherSales;
	}

	/**
	 * その他計（返品金額＋値引金額＋その他売上金額＋相殺金額）を設定します。
	 * @param otherSales その他計（返品金額＋値引金額＋その他売上金額＋相殺金額）
	 */
	public void setOtherSales(final java.math.BigDecimal otherSales) {
		this.otherSales = otherSales;
	}

}
