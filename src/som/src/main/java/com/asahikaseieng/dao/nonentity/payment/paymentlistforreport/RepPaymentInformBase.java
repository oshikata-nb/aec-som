/*
 * Created on 2009/09/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepPaymentInformクラス.
 * @author kanri-user
 */
public class RepPaymentInformBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepPaymentInformBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション dataTotalDivision */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション paymentDate */
	public static final String paymentDate_COLUMN = "PAYMENT_DATE";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション supplierCd */
	public static final String supplierCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション paymentAmount */
	public static final String paymentAmount_COLUMN = "PAYMENT_AMOUNT";

	/** COLUMNアノテーション bankCd */
	public static final String bankCd_COLUMN = "BANK_CD";

	/** COLUMNアノテーション accountDivision */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/** COLUMNアノテーション accountNo */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/** COLUMNアノテーション noteDivision */
	public static final String noteDivision_COLUMN = "NOTE_DIVISION";

	/** COLUMNアノテーション noteNo */
	public static final String noteNo_COLUMN = "NOTE_NO";

	/** COLUMNアノテーション drawalDate */
	public static final String drawalDate_COLUMN = "DRAWAL_DATE";

	/** COLUMNアノテーション noteDate */
	public static final String noteDate_COLUMN = "NOTE_DATE";

	/** COLUMNアノテーション debitSectionCd */
	public static final String debitSectionCd_COLUMN = "DEBIT_SECTION_CD";

	/** COLUMNアノテーション debitTitleCd */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** COLUMNアノテーション debitSubTitleCd */
	public static final String debitSubTitleCd_COLUMN = "DEBIT_SUB_TITLE_CD";

	/** COLUMNアノテーション creditSectionCd */
	public static final String creditSectionCd_COLUMN = "CREDIT_SECTION_CD";

	/** COLUMNアノテーション creditTitleCd */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** COLUMNアノテーション creditSubTitleCd */
	public static final String creditSubTitleCd_COLUMN = "CREDIT_SUB_TITLE_CD";

	/** COLUMNアノテーション summary */
	public static final String summary_COLUMN = "SUMMARY";

	/** COLUMNアノテーション depositTargetDivision */
	public static final String depositTargetDivision_COLUMN = "DEPOSIT_TARGET_DIVISION";

	/** COLUMNアノテーション claimTargetDivision */
	public static final String claimTargetDivision_COLUMN = "CLAIM_TARGET_DIVISION";

	/** COLUMNアノテーション payableTargetDivision */
	public static final String payableTargetDivision_COLUMN = "PAYABLE_TARGET_DIVISION";

	/** COLUMNアノテーション paymentTargetDivision */
	public static final String paymentTargetDivision_COLUMN = "PAYMENT_TARGET_DIVISION";

	/** COLUMNアノテーション issueDate */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

	/** COLUMNアノテーション issuedDivision */
	public static final String issuedDivision_COLUMN = "ISSUED_DIVISION";

	/** COLUMNアノテーション depositUpdateDivision */
	public static final String depositUpdateDivision_COLUMN = "DEPOSIT_UPDATE_DIVISION";

	/** COLUMNアノテーション depositNo */
	public static final String depositNo_COLUMN = "DEPOSIT_NO";

	/** COLUMNアノテーション deliveryUpdateDate */
	public static final String deliveryUpdateDate_COLUMN = "DELIVERY_UPDATE_DATE";

	/** COLUMNアノテーション claimUpdateDivision */
	public static final String claimUpdateDivision_COLUMN = "CLAIM_UPDATE_DIVISION";

	/** COLUMNアノテーション claimNo */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/** COLUMNアノテーション invoiceUpdateDate */
	public static final String invoiceUpdateDate_COLUMN = "INVOICE_UPDATE_DATE";

	/** COLUMNアノテーション payableUpdateDivision */
	public static final String payableUpdateDivision_COLUMN = "PAYABLE_UPDATE_DIVISION";

	/** COLUMNアノテーション payableNo */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション payableUpdateDate */
	public static final String payableUpdateDate_COLUMN = "PAYABLE_UPDATE_DATE";

	/** COLUMNアノテーション paymentUpdateDivision */
	public static final String paymentUpdateDivision_COLUMN = "PAYMENT_UPDATE_DIVISION";

	/** COLUMNアノテーション paymentNo */
	public static final String paymentNo_COLUMN = "PAYMENT_NO";

	/** COLUMNアノテーション paymentUpdateDate */
	public static final String paymentUpdateDate_COLUMN = "PAYMENT_UPDATE_DATE";

	/** COLUMNアノテーション eraserCompleteDivision */
	public static final String eraserCompleteDivision_COLUMN = "ERASER_COMPLETE_DIVISION";

	/** COLUMNアノテーション eraserCompleteDate */
	public static final String eraserCompleteDate_COLUMN = "ERASER_COMPLETE_DATE";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedby */
	public static final String approvedby_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvaldate */
	public static final String approvaldate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション transmissionDate */
	public static final String transmissionDate_COLUMN = "TRANSMISSION_DATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション sumPaymentAmount */
	public static final String sumPaymentAmount_COLUMN = "SUM_PAYMENT_AMOUNT";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション stockReduction */
	public static final String stockReduction_COLUMN = "STOCK_REDUCTION";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション buyMonth */
	public static final String buyMonth_COLUMN = "BUY_MONTH";

	/** COLUMNアノテーション bankMasterName */
	public static final String bankMasterName_COLUMN = "BANK_MASTER_NAME";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション accountDivisionName */
	public static final String accountDivisionName_COLUMN = "ACCOUNT_DIVISION_NAME";

	/** COLUMNアノテーション accountNoVen */
	public static final String accountNoVen_COLUMN = "ACCOUNT_NO_VEN";

	/** COLUMNアノテーション closingDate */
	public static final String closingDate_COLUMN = "CLOSING_DATE";

	/** COLUMNアノテーション transferCommissionLoad */
	public static final String transferCommissionLoad_COLUMN = "TRANSFER_COMMISSION_LOAD";

	/** COLUMNアノテーション nextdayFlg */
	public static final String nextdayFlg_COLUMN = "NEXTDAY_FLG";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal dataType;

	private java.math.BigDecimal dataTotalDivision;

	private String categoryDivision;

	private java.sql.Timestamp paymentDate;

	private String slipNo;

	private java.math.BigDecimal rowNo;

	private String supplierCd;

	private String organizationCd;

	private java.math.BigDecimal paymentAmount;

	private String bankCd;

	private java.math.BigDecimal accountDivision;

	private String accountNo;

	private java.math.BigDecimal noteDivision;

	private String noteNo;

	private java.sql.Timestamp drawalDate;

	private java.sql.Timestamp noteDate;

	private String debitSectionCd;

	private String debitTitleCd;

	private String debitSubTitleCd;

	private String creditSectionCd;

	private String creditTitleCd;

	private String creditSubTitleCd;

	private String summary;

	private java.math.BigDecimal depositTargetDivision;

	private java.math.BigDecimal claimTargetDivision;

	private java.math.BigDecimal payableTargetDivision;

	private java.math.BigDecimal paymentTargetDivision;

	private java.sql.Timestamp issueDate;

	private java.math.BigDecimal issuedDivision;

	private java.math.BigDecimal depositUpdateDivision;

	private String depositNo;

	private java.sql.Timestamp deliveryUpdateDate;

	private java.math.BigDecimal claimUpdateDivision;

	private String claimNo;

	private java.sql.Timestamp invoiceUpdateDate;

	private java.math.BigDecimal payableUpdateDivision;

	private String payableNo;

	private java.sql.Timestamp payableUpdateDate;

	private java.math.BigDecimal paymentUpdateDivision;

	private String paymentNo;

	private java.sql.Timestamp paymentUpdateDate;

	private java.math.BigDecimal eraserCompleteDivision;

	private java.sql.Timestamp eraserCompleteDate;

	private java.math.BigDecimal approvalStatus;

	private String approvedby;

	private java.sql.Timestamp approvaldate;

	private java.sql.Timestamp transmissionDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private java.math.BigDecimal sumPaymentAmount;

	private java.math.BigDecimal stockingAmount;

	private java.math.BigDecimal stockReduction;

	private java.math.BigDecimal offsetAmount;

	private java.math.BigDecimal buyMonth;

	private String bankMasterName;

	private String venderName1;

	private String venderName2;

	private String accountDivisionName;

	private String accountNoVen;

	private java.math.BigDecimal closingDate;

	private java.math.BigDecimal transferCommissionLoad;

	private String nextdayFlg;

	//
	// インスタンスメソッド
	//

	/**
	 * dataType取得.
	 * @return dataType
	 */
	public java.math.BigDecimal getDataType() {
		return this.dataType;
	}

	/**
	 * dataType設定.
	 * @param dataType dataType
	 */
	public void setDataType(final java.math.BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * dataTotalDivision取得.
	 * @return dataTotalDivision
	 */
	public java.math.BigDecimal getDataTotalDivision() {
		return this.dataTotalDivision;
	}

	/**
	 * dataTotalDivision設定.
	 * @param dataTotalDivision dataTotalDivision
	 */
	public void setDataTotalDivision(final java.math.BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
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
	 * paymentDate取得.
	 * @return paymentDate
	 */
	public java.sql.Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	/**
	 * paymentDate設定.
	 * @param paymentDate paymentDate
	 */
	public void setPaymentDate(final java.sql.Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * slipNo取得.
	 * @return slipNo
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * slipNo設定.
	 * @param slipNo slipNo
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * rowNo取得.
	 * @return rowNo
	 */
	public java.math.BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * rowNo設定.
	 * @param rowNo rowNo
	 */
	public void setRowNo(final java.math.BigDecimal rowNo) {
		this.rowNo = rowNo;
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
	 * paymentAmount取得.
	 * @return paymentAmount
	 */
	public java.math.BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	/**
	 * paymentAmount設定.
	 * @param paymentAmount paymentAmount
	 */
	public void setPaymentAmount(final java.math.BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * bankCd取得.
	 * @return bankCd
	 */
	public String getBankCd() {
		return this.bankCd;
	}

	/**
	 * bankCd設定.
	 * @param bankCd bankCd
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * accountDivision取得.
	 * @return accountDivision
	 */
	public java.math.BigDecimal getAccountDivision() {
		return this.accountDivision;
	}

	/**
	 * accountDivision設定.
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final java.math.BigDecimal accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * accountNo取得.
	 * @return accountNo
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * accountNo設定.
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * noteDivision取得.
	 * @return noteDivision
	 */
	public java.math.BigDecimal getNoteDivision() {
		return this.noteDivision;
	}

	/**
	 * noteDivision設定.
	 * @param noteDivision noteDivision
	 */
	public void setNoteDivision(final java.math.BigDecimal noteDivision) {
		this.noteDivision = noteDivision;
	}

	/**
	 * noteNo取得.
	 * @return noteNo
	 */
	public String getNoteNo() {
		return this.noteNo;
	}

	/**
	 * noteNo設定.
	 * @param noteNo noteNo
	 */
	public void setNoteNo(final String noteNo) {
		this.noteNo = noteNo;
	}

	/**
	 * drawalDate取得.
	 * @return drawalDate
	 */
	public java.sql.Timestamp getDrawalDate() {
		return this.drawalDate;
	}

	/**
	 * drawalDate設定.
	 * @param drawalDate drawalDate
	 */
	public void setDrawalDate(final java.sql.Timestamp drawalDate) {
		this.drawalDate = drawalDate;
	}

	/**
	 * noteDate取得.
	 * @return noteDate
	 */
	public java.sql.Timestamp getNoteDate() {
		return this.noteDate;
	}

	/**
	 * noteDate設定.
	 * @param noteDate noteDate
	 */
	public void setNoteDate(final java.sql.Timestamp noteDate) {
		this.noteDate = noteDate;
	}

	/**
	 * debitSectionCd取得.
	 * @return debitSectionCd
	 */
	public String getDebitSectionCd() {
		return this.debitSectionCd;
	}

	/**
	 * debitSectionCd設定.
	 * @param debitSectionCd debitSectionCd
	 */
	public void setDebitSectionCd(final String debitSectionCd) {
		this.debitSectionCd = debitSectionCd;
	}

	/**
	 * debitTitleCd取得.
	 * @return debitTitleCd
	 */
	public String getDebitTitleCd() {
		return this.debitTitleCd;
	}

	/**
	 * debitTitleCd設定.
	 * @param debitTitleCd debitTitleCd
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * debitSubTitleCd取得.
	 * @return debitSubTitleCd
	 */
	public String getDebitSubTitleCd() {
		return this.debitSubTitleCd;
	}

	/**
	 * debitSubTitleCd設定.
	 * @param debitSubTitleCd debitSubTitleCd
	 */
	public void setDebitSubTitleCd(final String debitSubTitleCd) {
		this.debitSubTitleCd = debitSubTitleCd;
	}

	/**
	 * creditSectionCd取得.
	 * @return creditSectionCd
	 */
	public String getCreditSectionCd() {
		return this.creditSectionCd;
	}

	/**
	 * creditSectionCd設定.
	 * @param creditSectionCd creditSectionCd
	 */
	public void setCreditSectionCd(final String creditSectionCd) {
		this.creditSectionCd = creditSectionCd;
	}

	/**
	 * creditTitleCd取得.
	 * @return creditTitleCd
	 */
	public String getCreditTitleCd() {
		return this.creditTitleCd;
	}

	/**
	 * creditTitleCd設定.
	 * @param creditTitleCd creditTitleCd
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * creditSubTitleCd取得.
	 * @return creditSubTitleCd
	 */
	public String getCreditSubTitleCd() {
		return this.creditSubTitleCd;
	}

	/**
	 * creditSubTitleCd設定.
	 * @param creditSubTitleCd creditSubTitleCd
	 */
	public void setCreditSubTitleCd(final String creditSubTitleCd) {
		this.creditSubTitleCd = creditSubTitleCd;
	}

	/**
	 * summary取得.
	 * @return summary
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * summary設定.
	 * @param summary summary
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * depositTargetDivision取得.
	 * @return depositTargetDivision
	 */
	public java.math.BigDecimal getDepositTargetDivision() {
		return this.depositTargetDivision;
	}

	/**
	 * depositTargetDivision設定.
	 * @param depositTargetDivision depositTargetDivision
	 */
	public void setDepositTargetDivision(final java.math.BigDecimal depositTargetDivision) {
		this.depositTargetDivision = depositTargetDivision;
	}

	/**
	 * claimTargetDivision取得.
	 * @return claimTargetDivision
	 */
	public java.math.BigDecimal getClaimTargetDivision() {
		return this.claimTargetDivision;
	}

	/**
	 * claimTargetDivision設定.
	 * @param claimTargetDivision claimTargetDivision
	 */
	public void setClaimTargetDivision(final java.math.BigDecimal claimTargetDivision) {
		this.claimTargetDivision = claimTargetDivision;
	}

	/**
	 * payableTargetDivision取得.
	 * @return payableTargetDivision
	 */
	public java.math.BigDecimal getPayableTargetDivision() {
		return this.payableTargetDivision;
	}

	/**
	 * payableTargetDivision設定.
	 * @param payableTargetDivision payableTargetDivision
	 */
	public void setPayableTargetDivision(final java.math.BigDecimal payableTargetDivision) {
		this.payableTargetDivision = payableTargetDivision;
	}

	/**
	 * paymentTargetDivision取得.
	 * @return paymentTargetDivision
	 */
	public java.math.BigDecimal getPaymentTargetDivision() {
		return this.paymentTargetDivision;
	}

	/**
	 * paymentTargetDivision設定.
	 * @param paymentTargetDivision paymentTargetDivision
	 */
	public void setPaymentTargetDivision(final java.math.BigDecimal paymentTargetDivision) {
		this.paymentTargetDivision = paymentTargetDivision;
	}

	/**
	 * issueDate取得.
	 * @return issueDate
	 */
	public java.sql.Timestamp getIssueDate() {
		return this.issueDate;
	}

	/**
	 * issueDate設定.
	 * @param issueDate issueDate
	 */
	public void setIssueDate(final java.sql.Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * issuedDivision取得.
	 * @return issuedDivision
	 */
	public java.math.BigDecimal getIssuedDivision() {
		return this.issuedDivision;
	}

	/**
	 * issuedDivision設定.
	 * @param issuedDivision issuedDivision
	 */
	public void setIssuedDivision(final java.math.BigDecimal issuedDivision) {
		this.issuedDivision = issuedDivision;
	}

	/**
	 * depositUpdateDivision取得.
	 * @return depositUpdateDivision
	 */
	public java.math.BigDecimal getDepositUpdateDivision() {
		return this.depositUpdateDivision;
	}

	/**
	 * depositUpdateDivision設定.
	 * @param depositUpdateDivision depositUpdateDivision
	 */
	public void setDepositUpdateDivision(final java.math.BigDecimal depositUpdateDivision) {
		this.depositUpdateDivision = depositUpdateDivision;
	}

	/**
	 * depositNo取得.
	 * @return depositNo
	 */
	public String getDepositNo() {
		return this.depositNo;
	}

	/**
	 * depositNo設定.
	 * @param depositNo depositNo
	 */
	public void setDepositNo(final String depositNo) {
		this.depositNo = depositNo;
	}

	/**
	 * deliveryUpdateDate取得.
	 * @return deliveryUpdateDate
	 */
	public java.sql.Timestamp getDeliveryUpdateDate() {
		return this.deliveryUpdateDate;
	}

	/**
	 * deliveryUpdateDate設定.
	 * @param deliveryUpdateDate deliveryUpdateDate
	 */
	public void setDeliveryUpdateDate(final java.sql.Timestamp deliveryUpdateDate) {
		this.deliveryUpdateDate = deliveryUpdateDate;
	}

	/**
	 * claimUpdateDivision取得.
	 * @return claimUpdateDivision
	 */
	public java.math.BigDecimal getClaimUpdateDivision() {
		return this.claimUpdateDivision;
	}

	/**
	 * claimUpdateDivision設定.
	 * @param claimUpdateDivision claimUpdateDivision
	 */
	public void setClaimUpdateDivision(final java.math.BigDecimal claimUpdateDivision) {
		this.claimUpdateDivision = claimUpdateDivision;
	}

	/**
	 * claimNo取得.
	 * @return claimNo
	 */
	public String getClaimNo() {
		return this.claimNo;
	}

	/**
	 * claimNo設定.
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * invoiceUpdateDate取得.
	 * @return invoiceUpdateDate
	 */
	public java.sql.Timestamp getInvoiceUpdateDate() {
		return this.invoiceUpdateDate;
	}

	/**
	 * invoiceUpdateDate設定.
	 * @param invoiceUpdateDate invoiceUpdateDate
	 */
	public void setInvoiceUpdateDate(final java.sql.Timestamp invoiceUpdateDate) {
		this.invoiceUpdateDate = invoiceUpdateDate;
	}

	/**
	 * payableUpdateDivision取得.
	 * @return payableUpdateDivision
	 */
	public java.math.BigDecimal getPayableUpdateDivision() {
		return this.payableUpdateDivision;
	}

	/**
	 * payableUpdateDivision設定.
	 * @param payableUpdateDivision payableUpdateDivision
	 */
	public void setPayableUpdateDivision(final java.math.BigDecimal payableUpdateDivision) {
		this.payableUpdateDivision = payableUpdateDivision;
	}

	/**
	 * payableNo取得.
	 * @return payableNo
	 */
	public String getPayableNo() {
		return this.payableNo;
	}

	/**
	 * payableNo設定.
	 * @param payableNo payableNo
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * payableUpdateDate取得.
	 * @return payableUpdateDate
	 */
	public java.sql.Timestamp getPayableUpdateDate() {
		return this.payableUpdateDate;
	}

	/**
	 * payableUpdateDate設定.
	 * @param payableUpdateDate payableUpdateDate
	 */
	public void setPayableUpdateDate(final java.sql.Timestamp payableUpdateDate) {
		this.payableUpdateDate = payableUpdateDate;
	}

	/**
	 * paymentUpdateDivision取得.
	 * @return paymentUpdateDivision
	 */
	public java.math.BigDecimal getPaymentUpdateDivision() {
		return this.paymentUpdateDivision;
	}

	/**
	 * paymentUpdateDivision設定.
	 * @param paymentUpdateDivision paymentUpdateDivision
	 */
	public void setPaymentUpdateDivision(final java.math.BigDecimal paymentUpdateDivision) {
		this.paymentUpdateDivision = paymentUpdateDivision;
	}

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
	 * paymentUpdateDate取得.
	 * @return paymentUpdateDate
	 */
	public java.sql.Timestamp getPaymentUpdateDate() {
		return this.paymentUpdateDate;
	}

	/**
	 * paymentUpdateDate設定.
	 * @param paymentUpdateDate paymentUpdateDate
	 */
	public void setPaymentUpdateDate(final java.sql.Timestamp paymentUpdateDate) {
		this.paymentUpdateDate = paymentUpdateDate;
	}

	/**
	 * eraserCompleteDivision取得.
	 * @return eraserCompleteDivision
	 */
	public java.math.BigDecimal getEraserCompleteDivision() {
		return this.eraserCompleteDivision;
	}

	/**
	 * eraserCompleteDivision設定.
	 * @param eraserCompleteDivision eraserCompleteDivision
	 */
	public void setEraserCompleteDivision(final java.math.BigDecimal eraserCompleteDivision) {
		this.eraserCompleteDivision = eraserCompleteDivision;
	}

	/**
	 * eraserCompleteDate取得.
	 * @return eraserCompleteDate
	 */
	public java.sql.Timestamp getEraserCompleteDate() {
		return this.eraserCompleteDate;
	}

	/**
	 * eraserCompleteDate設定.
	 * @param eraserCompleteDate eraserCompleteDate
	 */
	public void setEraserCompleteDate(final java.sql.Timestamp eraserCompleteDate) {
		this.eraserCompleteDate = eraserCompleteDate;
	}

	/**
	 * approvalStatus取得.
	 * @return approvalStatus
	 */
	public java.math.BigDecimal getApprovalStatus() {
		return this.approvalStatus;
	}

	/**
	 * approvalStatus設定.
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final java.math.BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * approvedby取得.
	 * @return approvedby
	 */
	public String getApprovedby() {
		return this.approvedby;
	}

	/**
	 * approvedby設定.
	 * @param approvedby approvedby
	 */
	public void setApprovedby(final String approvedby) {
		this.approvedby = approvedby;
	}

	/**
	 * approvaldate取得.
	 * @return approvaldate
	 */
	public java.sql.Timestamp getApprovaldate() {
		return this.approvaldate;
	}

	/**
	 * approvaldate設定.
	 * @param approvaldate approvaldate
	 */
	public void setApprovaldate(final java.sql.Timestamp approvaldate) {
		this.approvaldate = approvaldate;
	}

	/**
	 * transmissionDate取得.
	 * @return transmissionDate
	 */
	public java.sql.Timestamp getTransmissionDate() {
		return this.transmissionDate;
	}

	/**
	 * transmissionDate設定.
	 * @param transmissionDate transmissionDate
	 */
	public void setTransmissionDate(final java.sql.Timestamp transmissionDate) {
		this.transmissionDate = transmissionDate;
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
	 * sumPaymentAmount取得.
	 * @return sumPaymentAmount
	 */
	public java.math.BigDecimal getSumPaymentAmount() {
		return this.sumPaymentAmount;
	}

	/**
	 * sumPaymentAmount設定.
	 * @param sumPaymentAmount sumPaymentAmount
	 */
	public void setSumPaymentAmount(final java.math.BigDecimal sumPaymentAmount) {
		this.sumPaymentAmount = sumPaymentAmount;
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
	 * buyMonth取得.
	 * @return buyMonth
	 */
	public java.math.BigDecimal getBuyMonth() {
		return this.buyMonth;
	}

	/**
	 * buyMonth設定.
	 * @param buyMonth buyMonth
	 */
	public void setBuyMonth(final java.math.BigDecimal buyMonth) {
		this.buyMonth = buyMonth;
	}

	/**
	 * bankMasterName取得.
	 * @return bankMasterName
	 */
	public String getBankMasterName() {
		return this.bankMasterName;
	}

	/**
	 * bankMasterName設定.
	 * @param bankMasterName bankMasterName
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderName2取得.
	 * @return venderName2
	 */
	public String getVenderName2() {
		return this.venderName2;
	}

	/**
	 * venderName2設定.
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * accountDivisionName取得.
	 * @return accountDivisionName
	 */
	public String getAccountDivisionName() {
		return this.accountDivisionName;
	}

	/**
	 * accountDivisionName設定.
	 * @param accountDivisionName accountDivisionName
	 */
	public void setAccountDivisionName(final String accountDivisionName) {
		this.accountDivisionName = accountDivisionName;
	}

	/**
	 * accountNoVen取得.
	 * @return accountNoVen
	 */
	public String getAccountNoVen() {
		return this.accountNoVen;
	}

	/**
	 * accountNoVen設定.
	 * @param accountNoVen accountNoVen
	 */
	public void setAccountNoVen(final String accountNoVen) {
		this.accountNoVen = accountNoVen;
	}

	/**
	 * closingDate取得.
	 * @return closingDate
	 */
	public java.math.BigDecimal getClosingDate() {
		return this.closingDate;
	}

	/**
	 * closingDate設定.
	 * @param closingDate closingDate
	 */
	public void setClosingDate(final java.math.BigDecimal closingDate) {
		this.closingDate = closingDate;
	}

	/**
	 * transferCommissionLoad取得.
	 * @return transferCommissionLoad
	 */
	public java.math.BigDecimal getTransferCommissionLoad() {
		return this.transferCommissionLoad;
	}

	/**
	 * transferCommissionLoad設定.
	 * @param transferCommissionLoad transferCommissionLoad
	 */
	public void setTransferCommissionLoad(final java.math.BigDecimal transferCommissionLoad) {
		this.transferCommissionLoad = transferCommissionLoad;
	}

	/**
	 * nextdayFlg取得.
	 * @return nextdayFlg
	 */
	public String getNextdayFlg() {
		return this.nextdayFlg;
	}

	/**
	 * nextdayFlg設定.
	 * @param nextdayFlg nextdayFlg
	 */
	public void setNextdayFlg(final String nextdayFlg) {
		this.nextdayFlg = nextdayFlg;
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

