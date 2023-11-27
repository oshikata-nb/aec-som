/*
 * Created on 2008/07/01
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
 * 買掛元帳用クラス.
 * @author tosco
 */
public class ApLedgerDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApLedgerDetailBase() {
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

	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション paymentAmount */
	public static final String paymentAmount_COLUMN = "PAYMENT_AMOUNT";

	/** COLUMNアノテーション otherPaymentAmount */
	public static final String otherPaymentAmount_COLUMN = "OTHER_PAYMENT_AMOUNT";

	/** COLUMNアノテーション returnedAmount */
	public static final String returnedAmount_COLUMN = "STOCKING_RETURNED_AMOUNT";

	/** COLUMNアノテーション discountAmount */
	public static final String discountAmount_COLUMN = "STOCKING_DISCOUNT_AMOUNT";

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

	/** COLUMNアノテーション tranDivi */
	public static final String tranDivi_COLUMN = "TRAN_DIVI";

	/** COLUMNアノテーション tranDate */
	public static final String tranDate_COLUMN = "TRAN_DATE";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション stocking */
	public static final String stocking_COLUMN = "STOCKING";

	/** COLUMNアノテーション payment */
	public static final String payment_COLUMN = "PAYMENT";

	/** COLUMNアノテーション balance */
	public static final String balance_COLUMN = "BALANCE";

	//
	// インスタンスフィールド
	//

	/** 買掛番号 */
	private String payableNo;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 取引先コード */
	private String venderCd;

	/** 取引先名称 */
	private String venderName1;

	/** 支払先略称 */
	private String venderShortedName;

	/** 先月繰越 */
	private BigDecimal balanceForward;

	/** 支払額 */
	private BigDecimal paymentAmount;

	/** その他 */
	private BigDecimal otherPaymentAmount;

	/** 仕入高 */
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

	/** 買掛残高 */
	private BigDecimal payableAmount;

	/** 買掛金(内訳) */
	private BigDecimal accountPayableBreakdown;

	/** 未払金(内訳) */
	private BigDecimal arrearageBreakdown;

	/** 以外(内訳) */
	private BigDecimal exceptBreakdown;

	/** 処理区分 */
	private String tranDivi;

	/** 処理日付 */
	private String tranDate;

	/** 分類名称 */
	private String categoryName;

	/** 伝票番号 */
	private String slipNo;

	/** 行番号 */
	private BigDecimal rowNo;

	/** 品目／摘要／科目 */
	private String itemName;

	/** 仕入金額 */
	private BigDecimal stocking;

	/** 支払金額 */
	private BigDecimal payment;

	/** 残高 */
	private BigDecimal balance;

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
	 * 取引先コードを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 取引先コードを設定します。
	 * @param venderCd 取引先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 取引先名称Nameを取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 取引先名称を設定します。
	 * @param venderName1 取引先名称
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
	 * @return otherStockingAmount
	 */
	public BigDecimal getOtherStockingAmount() {
		return otherStockingAmount;
	}

	/**
	 * その他売上金額を設定します。
	 * @param otherStockingAmount その他売上金額
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
	 * 消費税額を取得します。
	 * @return taxAmount
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	/**
	 * 消費税額を設定します。
	 * @param taxAmount 消費税額
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
	 * 処理区分を取得します。
	 * @return tranDivi
	 */
	public String getTranDivi() {
		return tranDivi;
	}

	/**
	 * 処理区分を設定します。
	 * @param tranDivi 処理区分
	 */
	public void setTranDivi(final String tranDivi) {
		this.tranDivi = tranDivi;
	}

	/**
	 * 処理日付を取得します。
	 * @return tranDate
	 */
	public String getTranDate() {
		return tranDate;
	}

	/**
	 * 処理日付を設定します。
	 * @param tranDate 処理日付
	 */
	public void setTranDate(final String tranDate) {
		this.tranDate = tranDate;
	}

	/**
	 * 分類名称を取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類名称を設定します。
	 * @param categoryName 分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 伝票番号を取得します。
	 * @return slipNo
	 */
	public String getSlipNo() {
		return slipNo;
	}

	/**
	 * 伝票番号を設定します。
	 * @param slipNo 伝票番号
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * 行番号を取得します。
	 * @return rowNo
	 */
	public BigDecimal getRowNo() {
		return rowNo;
	}

	/**
	 * 行番号を設定します。
	 * @param rowNo 行番号
	 */
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * 品目／摘要を取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目／摘要を設定します。
	 * @param itemName 品目／摘要
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 売上高を取得します。
	 * @return stocking
	 */
	public BigDecimal getStocking() {
		return stocking;
	}

	/**
	 * 売上高を設定します。
	 * @param stocking 売上高
	 */
	public void setStocking(final BigDecimal stocking) {
		this.stocking = stocking;
	}

	/**
	 * 入金額を取得します。
	 * @return payment
	 */
	public BigDecimal getPayment() {
		return payment;
	}

	/**
	 * 入金額を設定します。
	 * @param payment 入金額
	 */
	public void setPayment(final BigDecimal payment) {
		this.payment = payment;
	}

	/**
	 * 残高を取得します。
	 * @return balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 残高を設定します。
	 * @param balance 残高
	 */
	public void setBalance(final BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 買掛金(内訳)を取得します。
	 * @return accountPayableBreakdown
	 */
	public BigDecimal getAccountPayableBreakdown() {
		return accountPayableBreakdown;
	}

	/**
	 * 買掛金(内訳)を設定します。
	 * @param accountPayableBreakdown 買掛金(内訳)
	 */
	public void setAccountPayableBreakdown(
			final BigDecimal accountPayableBreakdown) {
		this.accountPayableBreakdown = accountPayableBreakdown;
	}

	/**
	 * 未払金(内訳)を取得します。
	 * @return arrearageBreakdown
	 */
	public BigDecimal getArrearageBreakdown() {
		return arrearageBreakdown;
	}

	/**
	 * 未払金(内訳)を設定します。
	 * @param arrearageBreakdown 未払金(内訳)
	 */
	public void setArrearageBreakdown(final BigDecimal arrearageBreakdown) {
		this.arrearageBreakdown = arrearageBreakdown;
	}

	/**
	 * 以外(内訳)を取得します。
	 * @return exceptBreakdown
	 */
	public BigDecimal getExceptBreakdown() {
		return exceptBreakdown;
	}

	/**
	 * 以外(内訳)を設定します。
	 * @param exceptBreakdown 以外(内訳)
	 */
	public void setExceptBreakdown(final BigDecimal exceptBreakdown) {
		this.exceptBreakdown = exceptBreakdown;
	}

}
