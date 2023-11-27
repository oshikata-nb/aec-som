/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentbalance;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 支払残高一覧用Daoクラス.
 * @author tosco
 */
public class PaymentBalanceListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PaymentBalanceListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション paymentNo */
	public static final String paymentNo_COLUMN = "PAYMENT_NO";

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

	/** COLUMNアノテーション claimAmountForward */
	public static final String claimAmountForward_COLUMN = "CLAIM_AMOUNT_FORWARD";

	/** COLUMNアノテーション creditAmountForward */
	public static final String creditAmountForward_COLUMN = "CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション otherCreditAmountForward */
	public static final String otherCreditAmountForward_COLUMN = "OTHER_CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション stockingReturnedAmount */
	public static final String stockingReturnedAmount_COLUMN = "STOCKING_RETURNED_AMOUNT";

	/** COLUMNアノテーション stockingDisciuntAmount */
	public static final String stockingDisciuntAmount_COLUMN = "STOCKING_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション otherStockingAmount */
	public static final String otherStockingAmount_COLUMN = "OTHER_STOCKING_AMOUNT";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション payableAmount */
	public static final String payableAmount_COLUMN = "PAYABLE_AMOUNT";

	/** COLUMNアノテーション creditAmount */
	public static final String creditAmount_COLUMN = "CREDIT_AMOUNT";

	/** COLUMNアノテーション otherTotal */
	public static final String otherTotal_COLUMN = "OTHER_TOTAL";

	//
	// インスタンスフィールド
	//

	// 支払番号
	private String paymentNo;

	// 部署コード
	private String organizationCd;

	// 部署名称
	private String organizationName;

	// 支払先コード
	private String venderCd;

	// 支払先名称
	private String venderName1;

	// 支払先略称
	private String venderShortedName;

	// 支払予定額
	private BigDecimal claimAmountForward;

	// 前回支払額
	private BigDecimal creditAmountForward;

	// 前回その他支払額
	private BigDecimal otherCreditAmountForward;

	// 差引繰越額
	private BigDecimal balanceForward;

	// 今回仕入額
	private BigDecimal stockingAmount;

	// 返品金額
	private BigDecimal stockingReturnedAmount;

	// 値引金額
	private BigDecimal stockingDisciuntAmount;

	// その他仕入金額
	private BigDecimal otherStockingAmount;

	// 相殺
	private BigDecimal offsetAmount;

	// 消費税
	private BigDecimal taxAmount;

	// 支払残高
	private BigDecimal payableAmount;

	// 支払・その他計(前回支払額＋前回その他支払額)
	private BigDecimal creditAmount;

	// その他計(返品＋値引＋その他＋相殺)
	private BigDecimal otherTotal;

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
	 * 差引繰越額を取得します。
	 * @return balanceForward
	 */
	public BigDecimal getBalanceForward() {
		return balanceForward;
	}

	/**
	 * 差引繰越額を設定します。
	 * @param balanceForward 差引繰越額
	 */
	public void setBalanceForward(final BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * 支払予定額を取得します。
	 * @return claimAmountForward
	 */
	public BigDecimal getClaimAmountForward() {
		return claimAmountForward;
	}

	/**
	 * 支払予定額を設定します。
	 * @param claimAmountForward 支払予定額
	 */
	public void setClaimAmountForward(final BigDecimal claimAmountForward) {
		this.claimAmountForward = claimAmountForward;
	}

	/**
	 * 前回支払額を取得します。
	 * @return creditAmountForward
	 */
	public BigDecimal getCreditAmountForward() {
		return creditAmountForward;
	}

	/**
	 * 前回支払額を設定します。
	 * @param creditAmountForward 前回支払額
	 */
	public void setCreditAmountForward(final BigDecimal creditAmountForward) {
		this.creditAmountForward = creditAmountForward;
	}

	/**
	 * 前回その他支払額を取得します。
	 * @return otherCreditAmountForward
	 */
	public BigDecimal getOtherCreditAmountForward() {
		return otherCreditAmountForward;
	}

	/**
	 * 前回その他支払額を設定します。
	 * @param otherCreditAmountForward 前回その他支払額
	 */
	public void setOtherCreditAmountForward(
			final BigDecimal otherCreditAmountForward) {
		this.otherCreditAmountForward = otherCreditAmountForward;
	}

	/**
	 * その他計(返品＋値引＋その他＋相殺)を取得します。
	 * @return otherTotal
	 */
	public BigDecimal getOtherTotal() {
		return otherTotal;
	}

	/**
	 * その他計(返品＋値引＋その他＋相殺)を設定します。
	 * @param otherTotal その他計(返品＋値引＋その他＋相殺)
	 */
	public void setOtherTotal(final BigDecimal otherTotal) {
		this.otherTotal = otherTotal;
	}

	/**
	 * 支払残高を取得します。
	 * @return payableAmount
	 */
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}

	/**
	 * 支払残高を設定します。
	 * @param payableAmount 支払残高
	 */
	public void setPayableAmount(final BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}

	/**
	 * 支払番号を取得します。
	 * @return 支払番号 paymentNo
	 */
	public String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * 支払番号を設定します。
	 * @param paymentNo 支払番号
	 */
	public void setPaymentNo(final String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * 部署コードを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称を取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称を設定します。
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 今回仕入額を取得します。
	 * @return stockingAmount
	 */
	public BigDecimal getStockingAmount() {
		return stockingAmount;
	}

	/**
	 * 今回仕入額を設定します。
	 * @param stockingAmount 今回仕入額
	 */
	public void setStockingAmount(final BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * 値引金額を取得します。
	 * @return stockingDisciuntAmount
	 */
	public BigDecimal getStockingDisciuntAmount() {
		return stockingDisciuntAmount;
	}

	/**
	 * 値引金額を設定します。
	 * @param stockingDisciuntAmount 値引金額
	 */
	public void setStockingDisciuntAmount(
			final BigDecimal stockingDisciuntAmount) {
		this.stockingDisciuntAmount = stockingDisciuntAmount;
	}

	/**
	 * 返品金額を取得します。
	 * @return stockingReturnedAmount
	 */
	public BigDecimal getStockingReturnedAmount() {
		return stockingReturnedAmount;
	}

	/**
	 * 返品金額を設定します。
	 * @param stockingReturnedAmount 返品金額
	 */
	public void setStockingReturnedAmount(
			final BigDecimal stockingReturnedAmount) {
		this.stockingReturnedAmount = stockingReturnedAmount;
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
	 * 支払・その他計(前回支払額＋前回その他支払額)を取得します。
	 * @return creditAmount
	 */
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	/**
	 * 支払・その他計(前回支払額＋前回その他支払額)を設定します。
	 * @param creditAmount 支払・その他計(前回支払額＋前回その他支払額)
	 */
	public void setCreditAmount(final BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * 相殺を取得します。
	 * @return offsetAmount
	 */
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * 相殺を設定します。
	 * @param offsetAmount 相殺
	 */
	public void setOffsetAmount(final BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
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

}
