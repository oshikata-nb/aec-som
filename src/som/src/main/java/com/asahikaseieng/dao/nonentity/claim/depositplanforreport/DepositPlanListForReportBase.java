/*
 * Created on 2009/10/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositplanforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DepositPlanListForReportクラス.
 * @author t0011036
 */
public class DepositPlanListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DepositPlanListForReportBase() {
	}

	//
	// 定数
	//

	/*  */
	/** COLUMNアノテーション claimNo */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/*  */
	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/*  */
	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/*  */
	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/*  */
	/** COLUMNアノテーション creditScheduledDate */
	public static final String creditScheduledDate_COLUMN = "CREDIT_SCHEDULED_DATE";

	/*  */
	/** COLUMNアノテーション creditDivision */
	public static final String creditDivision_COLUMN = "CREDIT_DIVISION";

	/*  */
	/** COLUMNアノテーション noteSight */
	public static final String noteSight_COLUMN = "NOTE_SIGHT";

	/*  */
	/** COLUMNアノテーション holidayFlg */
	public static final String holidayFlg_COLUMN = "HOLIDAY_FLG";

	/*  */
	/** COLUMNアノテーション claimAmountForward */
	public static final String claimAmountForward_COLUMN = "CLAIM_AMOUNT_FORWARD";

	/*  */
	/** COLUMNアノテーション creditAmountForward */
	public static final String creditAmountForward_COLUMN = "CREDIT_AMOUNT_FORWARD";

	/*  */
	/** COLUMNアノテーション otherCreditAmountForward */
	public static final String otherCreditAmountForward_COLUMN = "OTHER_CREDIT_AMOUNT_FORWARD";

	/*  */
	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/*  */
	/** COLUMNアノテーション salesAmount */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/*  */
	/** COLUMNアノテーション salesReturnedAmount */
	public static final String salesReturnedAmount_COLUMN = "SALES_RETURNED_AMOUNT";

	/*  */
	/** COLUMNアノテーション salesDiscountAmount */
	public static final String salesDiscountAmount_COLUMN = "SALES_DISCOUNT_AMOUNT";

	/*  */
	/** COLUMNアノテーション otherSalesAmount */
	public static final String otherSalesAmount_COLUMN = "OTHER_SALES_AMOUNT";

	/*  */
	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/*  */
	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/*  */
	/** COLUMNアノテーション claimAmount */
	public static final String claimAmount_COLUMN = "CLAIM_AMOUNT";

	/*  */
	/** COLUMNアノテーション claimBalanceAmount */
	public static final String claimBalanceAmount_COLUMN = "CLAIM_BALANCE_AMOUNT";

	/*  */
	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/*  */
	/** COLUMNアノテーション eraserBalanceAmount */
	public static final String eraserBalanceAmount_COLUMN = "ERASER_BALANCE_AMOUNT";

	/*  */
	/** COLUMNアノテーション billDivision */
	public static final String billDivision_COLUMN = "BILL_DIVISION";

	/*  */
	/** COLUMNアノテーション issueDate */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

	/*  */
	/** COLUMNアノテーション issuerCd */
	public static final String issuerCd_COLUMN = "ISSUER_CD";

	/*  */
	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/*  */
	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/*  */
	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/*  */
	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/*  */
	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/*  */
	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/*  */
	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/*  */
	/** COLUMNアノテーション bankMasterCd */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/*  */
	/** COLUMNアノテーション bankMasterName */
	public static final String bankMasterName_COLUMN = "BANK_MASTER_NAME";

	/*  */
	/** COLUMNアノテーション accountDivision */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/*  */
	/** COLUMNアノテーション accountNo */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/*  */
	/** COLUMNアノテーション advanceDivision */
	public static final String advanceDivision_COLUMN = "ADVANCE_DIVISION";

	/*  */
	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/*  */
	/** COLUMNアノテーション claimAmountBalance */
	public static final String claimAmountBalance_COLUMN = "CLAIM_AMOUNT_BALANCE";

	/*  */
	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/*  */
	/** COLUMNアノテーション useBankMasterCd */
	public static final String useBankMasterCd_COLUMN = "USE_BANK_MASTER_CD";

	/*  */
	/** COLUMNアノテーション useBankMasterName */
	public static final String useBankMasterName_COLUMN = "USE_BANK_MASTER_NAME";

	/*  */
	/** COLUMNアノテーション useAccountDivision */
	public static final String useAccountDivision_COLUMN = "USE_ACCOUNT_DIVISION";

	/*  */
	/** COLUMNアノテーション useAccountNo */
	public static final String useAccountNo_COLUMN = "USE_ACCOUNT_NO";

	/*  */
	/** COLUMNアノテーション issuerName */
	public static final String issuerName_COLUMN = "ISSUER_NAME";

	/*  */
	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/*  */
	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	/*  */
	/** COLUMNアノテーション invoiceTaxAmount */
	public static final String invoiceTaxAmount_COLUMN = "INVOICE_TAX_AMOUNT";

	/*  */
	/** COLUMNアノテーション salesTaxAmount */
	public static final String salesTaxAmount_COLUMN = "SALES_TAX_AMOUNT";

	/*  */
	/** COLUMNアノテーション taxDifference */
	public static final String taxDifference_COLUMN = "TAX_DIFFERENCE";

	//
	// インスタンスフィールド
	//

	/**  */
	private String claimNo;

	/**  */
	private String organizationCd;

	/**  */
	private String venderCd;

	/**  */
	private java.sql.Timestamp creditDate;

	/**  */
	private java.sql.Timestamp creditScheduledDate;

	/**  */
	private String creditDivision;

	/**  */
	private java.math.BigDecimal noteSight;

	/**  */
	private java.math.BigDecimal holidayFlg;

	/**  */
	private java.math.BigDecimal claimAmountForward;

	/**  */
	private java.math.BigDecimal creditAmountForward;

	/**  */
	private java.math.BigDecimal otherCreditAmountForward;

	/**  */
	private java.math.BigDecimal balanceForward;

	/**  */
	private java.math.BigDecimal salesAmount;

	/**  */
	private java.math.BigDecimal salesReturnedAmount;

	/**  */
	private java.math.BigDecimal salesDiscountAmount;

	/**  */
	private java.math.BigDecimal otherSalesAmount;

	/**  */
	private java.math.BigDecimal offsetAmount;

	/**  */
	private java.math.BigDecimal taxAmount;

	/**  */
	private java.math.BigDecimal claimAmount;

	/**  */
	private java.math.BigDecimal claimBalanceAmount;

	/**  */
	private java.math.BigDecimal eraserAmount;

	/**  */
	private java.math.BigDecimal eraserBalanceAmount;

	/**  */
	private java.math.BigDecimal billDivision;

	/**  */
	private java.sql.Timestamp issueDate;

	/**  */
	private String issuerCd;

	/**  */
	private java.sql.Timestamp inputDate;

	/**  */
	private String inputorCd;

	/**  */
	private java.sql.Timestamp updateDate;

	/**  */
	private String updatorCd;

	/**  */
	private String venderDivision;

	/**  */
	private String venderName1;

	/**  */
	private String venderShortedName;

	/**  */
	private String bankMasterCd;

	/**  */
	private String bankMasterName;

	/**  */
	private java.math.BigDecimal accountDivision;

	/**  */
	private String accountNo;

	/**  */
	private java.math.BigDecimal advanceDivision;

	/**  */
	private String organizationName;

	/**  */
	private java.math.BigDecimal claimAmountBalance;

	/**  */
	private String categoryName;

	/**  */
	private String useBankMasterCd;

	/**  */
	private String useBankMasterName;

	/**  */
	private String useAccountDivision;

	/**  */
	private String useAccountNo;

	/**  */
	private String issuerName;

	/**  */
	private String inputorName;

	/**  */
	private String updatorName;

	/**  */
	private java.math.BigDecimal invoiceTaxAmount;

	/**  */
	private java.math.BigDecimal salesTaxAmount;

	/**  */
	private java.math.BigDecimal taxDifference;

	//
	// インスタンスメソッド
	//

	/**
	 * claimNo取得.
	 *
	 * @return claimNo
	 */
	public String getClaimNo() {
		return this.claimNo;
	}

	/**
	 * claimNo設定.
	 *
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * organizationCd取得.
	 *
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 *
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * venderCd取得.
	 *
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 *
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * creditDate取得.
	 *
	 * @return creditDate
	 */
	public java.sql.Timestamp getCreditDate() {
		return this.creditDate;
	}

	/**
	 * creditDate設定.
	 *
	 * @param creditDate creditDate
	 */
	public void setCreditDate(final java.sql.Timestamp creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * creditScheduledDate取得.
	 *
	 * @return creditScheduledDate
	 */
	public java.sql.Timestamp getCreditScheduledDate() {
		return this.creditScheduledDate;
	}

	/**
	 * creditScheduledDate設定.
	 *
	 * @param creditScheduledDate creditScheduledDate
	 */
	public void setCreditScheduledDate(final java.sql.Timestamp creditScheduledDate) {
		this.creditScheduledDate = creditScheduledDate;
	}

	/**
	 * creditDivision取得.
	 *
	 * @return creditDivision
	 */
	public String getCreditDivision() {
		return this.creditDivision;
	}

	/**
	 * creditDivision設定.
	 *
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final String creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * noteSight取得.
	 *
	 * @return noteSight
	 */
	public java.math.BigDecimal getNoteSight() {
		return this.noteSight;
	}

	/**
	 * noteSight設定.
	 *
	 * @param noteSight noteSight
	 */
	public void setNoteSight(final java.math.BigDecimal noteSight) {
		this.noteSight = noteSight;
	}

	/**
	 * holidayFlg取得.
	 *
	 * @return holidayFlg
	 */
	public java.math.BigDecimal getHolidayFlg() {
		return this.holidayFlg;
	}

	/**
	 * holidayFlg設定.
	 *
	 * @param holidayFlg holidayFlg
	 */
	public void setHolidayFlg(final java.math.BigDecimal holidayFlg) {
		this.holidayFlg = holidayFlg;
	}

	/**
	 * claimAmountForward取得.
	 *
	 * @return claimAmountForward
	 */
	public java.math.BigDecimal getClaimAmountForward() {
		return this.claimAmountForward;
	}

	/**
	 * claimAmountForward設定.
	 *
	 * @param claimAmountForward claimAmountForward
	 */
	public void setClaimAmountForward(final java.math.BigDecimal claimAmountForward) {
		this.claimAmountForward = claimAmountForward;
	}

	/**
	 * creditAmountForward取得.
	 *
	 * @return creditAmountForward
	 */
	public java.math.BigDecimal getCreditAmountForward() {
		return this.creditAmountForward;
	}

	/**
	 * creditAmountForward設定.
	 *
	 * @param creditAmountForward creditAmountForward
	 */
	public void setCreditAmountForward(final java.math.BigDecimal creditAmountForward) {
		this.creditAmountForward = creditAmountForward;
	}

	/**
	 * otherCreditAmountForward取得.
	 *
	 * @return otherCreditAmountForward
	 */
	public java.math.BigDecimal getOtherCreditAmountForward() {
		return this.otherCreditAmountForward;
	}

	/**
	 * otherCreditAmountForward設定.
	 *
	 * @param otherCreditAmountForward otherCreditAmountForward
	 */
	public void setOtherCreditAmountForward(final java.math.BigDecimal otherCreditAmountForward) {
		this.otherCreditAmountForward = otherCreditAmountForward;
	}

	/**
	 * balanceForward取得.
	 *
	 * @return balanceForward
	 */
	public java.math.BigDecimal getBalanceForward() {
		return this.balanceForward;
	}

	/**
	 * balanceForward設定.
	 *
	 * @param balanceForward balanceForward
	 */
	public void setBalanceForward(final java.math.BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * salesAmount取得.
	 *
	 * @return salesAmount
	 */
	public java.math.BigDecimal getSalesAmount() {
		return this.salesAmount;
	}

	/**
	 * salesAmount設定.
	 *
	 * @param salesAmount salesAmount
	 */
	public void setSalesAmount(final java.math.BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * salesReturnedAmount取得.
	 *
	 * @return salesReturnedAmount
	 */
	public java.math.BigDecimal getSalesReturnedAmount() {
		return this.salesReturnedAmount;
	}

	/**
	 * salesReturnedAmount設定.
	 *
	 * @param salesReturnedAmount salesReturnedAmount
	 */
	public void setSalesReturnedAmount(final java.math.BigDecimal salesReturnedAmount) {
		this.salesReturnedAmount = salesReturnedAmount;
	}

	/**
	 * salesDiscountAmount取得.
	 *
	 * @return salesDiscountAmount
	 */
	public java.math.BigDecimal getSalesDiscountAmount() {
		return this.salesDiscountAmount;
	}

	/**
	 * salesDiscountAmount設定.
	 *
	 * @param salesDiscountAmount salesDiscountAmount
	 */
	public void setSalesDiscountAmount(final java.math.BigDecimal salesDiscountAmount) {
		this.salesDiscountAmount = salesDiscountAmount;
	}

	/**
	 * otherSalesAmount取得.
	 *
	 * @return otherSalesAmount
	 */
	public java.math.BigDecimal getOtherSalesAmount() {
		return this.otherSalesAmount;
	}

	/**
	 * otherSalesAmount設定.
	 *
	 * @param otherSalesAmount otherSalesAmount
	 */
	public void setOtherSalesAmount(final java.math.BigDecimal otherSalesAmount) {
		this.otherSalesAmount = otherSalesAmount;
	}

	/**
	 * offsetAmount取得.
	 *
	 * @return offsetAmount
	 */
	public java.math.BigDecimal getOffsetAmount() {
		return this.offsetAmount;
	}

	/**
	 * offsetAmount設定.
	 *
	 * @param offsetAmount offsetAmount
	 */
	public void setOffsetAmount(final java.math.BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * taxAmount取得.
	 *
	 * @return taxAmount
	 */
	public java.math.BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * taxAmount設定.
	 *
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * claimAmount取得.
	 *
	 * @return claimAmount
	 */
	public java.math.BigDecimal getClaimAmount() {
		return this.claimAmount;
	}

	/**
	 * claimAmount設定.
	 *
	 * @param claimAmount claimAmount
	 */
	public void setClaimAmount(final java.math.BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * claimBalanceAmount取得.
	 *
	 * @return claimBalanceAmount
	 */
	public java.math.BigDecimal getClaimBalanceAmount() {
		return this.claimBalanceAmount;
	}

	/**
	 * claimBalanceAmount設定.
	 *
	 * @param claimBalanceAmount claimBalanceAmount
	 */
	public void setClaimBalanceAmount(final java.math.BigDecimal claimBalanceAmount) {
		this.claimBalanceAmount = claimBalanceAmount;
	}

	/**
	 * eraserAmount取得.
	 *
	 * @return eraserAmount
	 */
	public java.math.BigDecimal getEraserAmount() {
		return this.eraserAmount;
	}

	/**
	 * eraserAmount設定.
	 *
	 * @param eraserAmount eraserAmount
	 */
	public void setEraserAmount(final java.math.BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * eraserBalanceAmount取得.
	 *
	 * @return eraserBalanceAmount
	 */
	public java.math.BigDecimal getEraserBalanceAmount() {
		return this.eraserBalanceAmount;
	}

	/**
	 * eraserBalanceAmount設定.
	 *
	 * @param eraserBalanceAmount eraserBalanceAmount
	 */
	public void setEraserBalanceAmount(final java.math.BigDecimal eraserBalanceAmount) {
		this.eraserBalanceAmount = eraserBalanceAmount;
	}

	/**
	 * billDivision取得.
	 *
	 * @return billDivision
	 */
	public java.math.BigDecimal getBillDivision() {
		return this.billDivision;
	}

	/**
	 * billDivision設定.
	 *
	 * @param billDivision billDivision
	 */
	public void setBillDivision(final java.math.BigDecimal billDivision) {
		this.billDivision = billDivision;
	}

	/**
	 * issueDate取得.
	 *
	 * @return issueDate
	 */
	public java.sql.Timestamp getIssueDate() {
		return this.issueDate;
	}

	/**
	 * issueDate設定.
	 *
	 * @param issueDate issueDate
	 */
	public void setIssueDate(final java.sql.Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * issuerCd取得.
	 *
	 * @return issuerCd
	 */
	public String getIssuerCd() {
		return this.issuerCd;
	}

	/**
	 * issuerCd設定.
	 *
	 * @param issuerCd issuerCd
	 */
	public void setIssuerCd(final String issuerCd) {
		this.issuerCd = issuerCd;
	}

	/**
	 * inputDate取得.
	 *
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 *
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 *
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 *
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 *
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 *
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 *
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 *
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * venderDivision取得.
	 *
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * venderDivision設定.
	 *
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * venderName1取得.
	 *
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 *
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderShortedName取得.
	 *
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return this.venderShortedName;
	}

	/**
	 * venderShortedName設定.
	 *
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * bankMasterCd取得.
	 *
	 * @return bankMasterCd
	 */
	public String getBankMasterCd() {
		return this.bankMasterCd;
	}

	/**
	 * bankMasterCd設定.
	 *
	 * @param bankMasterCd bankMasterCd
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * bankMasterName取得.
	 *
	 * @return bankMasterName
	 */
	public String getBankMasterName() {
		return this.bankMasterName;
	}

	/**
	 * bankMasterName設定.
	 *
	 * @param bankMasterName bankMasterName
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * accountDivision取得.
	 *
	 * @return accountDivision
	 */
	public java.math.BigDecimal getAccountDivision() {
		return this.accountDivision;
	}

	/**
	 * accountDivision設定.
	 *
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final java.math.BigDecimal accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * accountNo取得.
	 *
	 * @return accountNo
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * accountNo設定.
	 *
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * advanceDivision取得.
	 *
	 * @return advanceDivision
	 */
	public java.math.BigDecimal getAdvanceDivision() {
		return this.advanceDivision;
	}

	/**
	 * advanceDivision設定.
	 *
	 * @param advanceDivision advanceDivision
	 */
	public void setAdvanceDivision(final java.math.BigDecimal advanceDivision) {
		this.advanceDivision = advanceDivision;
	}

	/**
	 * organizationName取得.
	 *
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 *
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * claimAmountBalance取得.
	 *
	 * @return claimAmountBalance
	 */
	public java.math.BigDecimal getClaimAmountBalance() {
		return this.claimAmountBalance;
	}

	/**
	 * claimAmountBalance設定.
	 *
	 * @param claimAmountBalance claimAmountBalance
	 */
	public void setClaimAmountBalance(final java.math.BigDecimal claimAmountBalance) {
		this.claimAmountBalance = claimAmountBalance;
	}

	/**
	 * categoryName取得.
	 *
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * categoryName設定.
	 *
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * useBankMasterCd取得.
	 *
	 * @return useBankMasterCd
	 */
	public String getUseBankMasterCd() {
		return this.useBankMasterCd;
	}

	/**
	 * useBankMasterCd設定.
	 *
	 * @param useBankMasterCd useBankMasterCd
	 */
	public void setUseBankMasterCd(final String useBankMasterCd) {
		this.useBankMasterCd = useBankMasterCd;
	}

	/**
	 * useBankMasterName取得.
	 *
	 * @return useBankMasterName
	 */
	public String getUseBankMasterName() {
		return this.useBankMasterName;
	}

	/**
	 * useBankMasterName設定.
	 *
	 * @param useBankMasterName useBankMasterName
	 */
	public void setUseBankMasterName(final String useBankMasterName) {
		this.useBankMasterName = useBankMasterName;
	}

	/**
	 * useAccountDivision取得.
	 *
	 * @return useAccountDivision
	 */
	public String getUseAccountDivision() {
		return this.useAccountDivision;
	}

	/**
	 * useAccountDivision設定.
	 *
	 * @param useAccountDivision useAccountDivision
	 */
	public void setUseAccountDivision(final String useAccountDivision) {
		this.useAccountDivision = useAccountDivision;
	}

	/**
	 * useAccountNo取得.
	 *
	 * @return useAccountNo
	 */
	public String getUseAccountNo() {
		return this.useAccountNo;
	}

	/**
	 * useAccountNo設定.
	 *
	 * @param useAccountNo useAccountNo
	 */
	public void setUseAccountNo(final String useAccountNo) {
		this.useAccountNo = useAccountNo;
	}

	/**
	 * issuerName取得.
	 *
	 * @return issuerName
	 */
	public String getIssuerName() {
		return this.issuerName;
	}

	/**
	 * issuerName設定.
	 *
	 * @param issuerName issuerName
	 */
	public void setIssuerName(final String issuerName) {
		this.issuerName = issuerName;
	}

	/**
	 * inputorName取得.
	 *
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 *
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 *
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 *
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
	}

	/**
	 * invoiceTaxAmount取得.
	 *
	 * @return invoiceTaxAmount
	 */
	public java.math.BigDecimal getInvoiceTaxAmount() {
		return this.invoiceTaxAmount;
	}

	/**
	 * invoiceTaxAmount設定.
	 *
	 * @param invoiceTaxAmount invoiceTaxAmount
	 */
	public void setInvoiceTaxAmount(final java.math.BigDecimal invoiceTaxAmount) {
		this.invoiceTaxAmount = invoiceTaxAmount;
	}

	/**
	 * salesTaxAmount取得.
	 *
	 * @return salesTaxAmount
	 */
	public java.math.BigDecimal getSalesTaxAmount() {
		return this.salesTaxAmount;
	}

	/**
	 * salesTaxAmount設定.
	 *
	 * @param salesTaxAmount salesTaxAmount
	 */
	public void setSalesTaxAmount(final java.math.BigDecimal salesTaxAmount) {
		this.salesTaxAmount = salesTaxAmount;
	}

	/**
	 * taxDifference取得.
	 *
	 * @return taxDifference
	 */
	public java.math.BigDecimal getTaxDifference() {
		return this.taxDifference;
	}

	/**
	 * taxDifference設定.
	 *
	 * @param taxDifference taxDifference
	 */
	public void setTaxDifference(final java.math.BigDecimal taxDifference) {
		this.taxDifference = taxDifference;
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

