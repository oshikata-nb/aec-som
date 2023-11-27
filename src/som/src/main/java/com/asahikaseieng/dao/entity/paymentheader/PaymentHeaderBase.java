/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Mon Jun 15 10:46:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.paymentheader;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PaymentHeaderBaseクラス.
 * @author a7710658
 */
public class PaymentHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PaymentHeaderBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PAYMENT_HEADER";


//	/** IDアノテーション paymentNo. */
//	public static final String paymentNo_ID = "assigned";

	/** COLUMNアノテーション paymentNo. */
	public static final String paymentNo_COLUMN = "PAYMENT_NO";

	/** COLUMNアノテーション organizationCd. */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション supplierCd. */
	public static final String supplierCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション payableDate. */
	public static final String payableDate_COLUMN = "PAYABLE_DATE";

	/** COLUMNアノテーション creditScheduledDate. */
	public static final String creditScheduledDate_COLUMN = "CREDIT_SCHEDULED_DATE";

	/** COLUMNアノテーション creditDivision. */
	public static final String creditDivision_COLUMN = "CREDIT_DIVISION";

	/** COLUMNアノテーション noteSight. */
	public static final String noteSight_COLUMN = "NOTE_SIGHT";

	/** COLUMNアノテーション holidayFlg. */
	public static final String holidayFlg_COLUMN = "HOLIDAY_FLG";

	/** COLUMNアノテーション claimAmountForward. */
	public static final String claimAmountForward_COLUMN = "CLAIM_AMOUNT_FORWARD";

	/** COLUMNアノテーション creditAmountForward. */
	public static final String creditAmountForward_COLUMN = "CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション otherCreditAmountForward. */
	public static final String otherCreditAmountForward_COLUMN = "OTHER_CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション balanceForward. */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション stockingAmount. */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション stockingReturnedAmount. */
	public static final String stockingReturnedAmount_COLUMN = "STOCKING_RETURNED_AMOUNT";

	/** COLUMNアノテーション stockingDiscountAmount. */
	public static final String stockingDiscountAmount_COLUMN = "STOCKING_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション otherStockingAmount. */
	public static final String otherStockingAmount_COLUMN = "OTHER_STOCKING_AMOUNT";

	/** COLUMNアノテーション offsetAmount. */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション taxAmount. */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション payableAmount. */
	public static final String payableAmount_COLUMN = "PAYABLE_AMOUNT";

	/** COLUMNアノテーション stockReduction. */
	public static final String stockReduction_COLUMN = "STOCK_REDUCTION";

	/** COLUMNアノテーション transferFee. */
	public static final String transferFee_COLUMN = "TRANSFER_FEE";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String paymentNo;

	private String organizationCd;

	private String supplierCd;

	private java.sql.Timestamp payableDate;

	private java.sql.Timestamp creditScheduledDate;

	private String creditDivision;

	private java.math.BigDecimal noteSight;

	private java.math.BigDecimal holidayFlg;

	private java.math.BigDecimal claimAmountForward;

	private java.math.BigDecimal creditAmountForward;

	private java.math.BigDecimal otherCreditAmountForward;

	private java.math.BigDecimal balanceForward;

	private java.math.BigDecimal stockingAmount;

	private java.math.BigDecimal stockingReturnedAmount;

	private java.math.BigDecimal stockingDiscountAmount;

	private java.math.BigDecimal otherStockingAmount;

	private java.math.BigDecimal offsetAmount;

	private java.math.BigDecimal taxAmount;

	private java.math.BigDecimal payableAmount;

	private java.math.BigDecimal stockReduction;

	private java.math.BigDecimal transferFee;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * paymentNo取得.
	 * @return paymentNo
	 */
	public String getPaymentNo() {
		return this.paymentNo;
	}

	/**
	 * paymentNo設定.
	 * @param paymentNo paymentNo
	 */
	public void setPaymentNo(final String paymentNo) {
		this.paymentNo = paymentNo;
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
	 * creditScheduledDate取得.
	 * @return creditScheduledDate
	 */
	public java.sql.Timestamp getCreditScheduledDate() {
		return this.creditScheduledDate;
	}

	/**
	 * creditScheduledDate設定.
	 * @param creditScheduledDate creditScheduledDate
	 */
	public void setCreditScheduledDate(final java.sql.Timestamp creditScheduledDate) {
		this.creditScheduledDate = creditScheduledDate;
	}

	/**
	 * creditDivision取得.
	 * @return creditDivision
	 */
	public String getCreditDivision() {
		return this.creditDivision;
	}

	/**
	 * creditDivision設定.
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final String creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * noteSight取得.
	 * @return noteSight
	 */
	public java.math.BigDecimal getNoteSight() {
		return this.noteSight;
	}

	/**
	 * noteSight設定.
	 * @param noteSight noteSight
	 */
	public void setNoteSight(final java.math.BigDecimal noteSight) {
		this.noteSight = noteSight;
	}

	/**
	 * holidayFlg取得.
	 * @return holidayFlg
	 */
	public java.math.BigDecimal getHolidayFlg() {
		return this.holidayFlg;
	}

	/**
	 * holidayFlg設定.
	 * @param holidayFlg holidayFlg
	 */
	public void setHolidayFlg(final java.math.BigDecimal holidayFlg) {
		this.holidayFlg = holidayFlg;
	}

	/**
	 * claimAmountForward取得.
	 * @return claimAmountForward
	 */
	public java.math.BigDecimal getClaimAmountForward() {
		return this.claimAmountForward;
	}

	/**
	 * claimAmountForward設定.
	 * @param claimAmountForward claimAmountForward
	 */
	public void setClaimAmountForward(final java.math.BigDecimal claimAmountForward) {
		this.claimAmountForward = claimAmountForward;
	}

	/**
	 * creditAmountForward取得.
	 * @return creditAmountForward
	 */
	public java.math.BigDecimal getCreditAmountForward() {
		return this.creditAmountForward;
	}

	/**
	 * creditAmountForward設定.
	 * @param creditAmountForward creditAmountForward
	 */
	public void setCreditAmountForward(final java.math.BigDecimal creditAmountForward) {
		this.creditAmountForward = creditAmountForward;
	}

	/**
	 * otherCreditAmountForward取得.
	 * @return otherCreditAmountForward
	 */
	public java.math.BigDecimal getOtherCreditAmountForward() {
		return this.otherCreditAmountForward;
	}

	/**
	 * otherCreditAmountForward設定.
	 * @param otherCreditAmountForward otherCreditAmountForward
	 */
	public void setOtherCreditAmountForward(final java.math.BigDecimal otherCreditAmountForward) {
		this.otherCreditAmountForward = otherCreditAmountForward;
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
	 * stockReduction取得.
	 * @return stockReduction
	 */
	public java.math.BigDecimal getStockReduction() {
		return this.stockReduction;
	}

	/**
	 * stockReduction設定.
	 * @param stockReduction stockReduction
	 */
	public void setStockReduction(final java.math.BigDecimal stockReduction) {
		this.stockReduction = stockReduction;
	}

	/**
	 * transferFee取得.
	 * @return transferFee
	 */
	public java.math.BigDecimal getTransferFee() {
		return this.transferFee;
	}

	/**
	 * transferFee設定.
	 * @param transferFee transferFee
	 */
	public void setTransferFee(final java.math.BigDecimal transferFee) {
		this.transferFee = transferFee;
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
