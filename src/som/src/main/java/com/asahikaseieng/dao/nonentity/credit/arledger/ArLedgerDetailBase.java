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
public class ArLedgerDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArLedgerDetailBase() {
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

	/** COLUMNアノテーション sales */
	public static final String sales_COLUMN = "SALES";

	/** COLUMNアノテーション credit */
	public static final String credit_COLUMN = "CREDIT";

	/** COLUMNアノテーション balance */
	public static final String balance_COLUMN = "BALANCE";

	//
	// インスタンスフィールド
	//

	/** 売掛番号 */
	private String depositNo;

	/** 部門コード */
	private String sectionCd;

	/** 部門名称 */
	private String sectionName;

	/** 取引先コード */
	private String venderCd;

	/** 取引先名称 */
	private String venderName;

	/** 取引先略称 */
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

	/** 売掛残高 */
	private BigDecimal creditAmount;

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

	/** 売上高 */
	private BigDecimal sales;

	/** 入金額 */
	private BigDecimal credit;

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
	 * 部門コードを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コードを設定します。
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 部門名称を取得します。
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部門名称を設定します。
	 * @param sectionName 部門名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
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
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 取引先名称を設定します。
	 * @param venderName 取引先名称
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
	 * @return sales
	 */
	public BigDecimal getSales() {
		return sales;
	}

	/**
	 * 売上高を設定します。
	 * @param sales 売上高
	 */
	public void setSales(final BigDecimal sales) {
		this.sales = sales;
	}

	/**
	 * 入金額を取得します。
	 * @return credit
	 */
	public BigDecimal getCredit() {
		return credit;
	}

	/**
	 * 入金額を設定します。
	 * @param credit 入金額
	 */
	public void setCredit(final BigDecimal credit) {
		this.credit = credit;
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

}
