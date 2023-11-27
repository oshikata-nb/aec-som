/*
 * Created on 2009/09/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepDepositDetailクラス.
 * @author kanri-user
 */
public class RepDepositDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepDepositDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション creditNo */
	public static final String creditNo_COLUMN = "CREDIT_NO";

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション dataTotalDivision */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション creditAmount */
	public static final String creditAmount_COLUMN = "CREDIT_AMOUNT";

	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション creditEraserAmount */
	public static final String creditEraserAmount_COLUMN = "CREDIT_ERASER_AMOUNT";

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

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedby */
	public static final String approvedby_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvaldate */
	public static final String approvaldate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション debitTitleName */
	public static final String debitTitleName_COLUMN = "DEBIT_TITLE_NAME";

	/** COLUMNアノテーション debitSectionName */
	public static final String debitSectionName_COLUMN = "DEBIT_SECTION_NAME";

	/** COLUMNアノテーション creditTitleName */
	public static final String creditTitleName_COLUMN = "CREDIT_TITLE_NAME";

	/** COLUMNアノテーション creditSectionName */
	public static final String creditSectionName_COLUMN = "CREDIT_SECTION_NAME";

	/** COLUMNアノテーション debitSeikyuCd */
	public static final String debitSeikyuCd_COLUMN = "DEBIT_SEIKYU_CD";

	/** COLUMNアノテーション debitSeikyuName */
	public static final String debitSeikyuName_COLUMN = "DEBIT_SEIKYU_NAME";

	/** COLUMNアノテーション creditSeikyuCd */
	public static final String creditSeikyuCd_COLUMN = "CREDIT_SEIKYU_CD";

	/** COLUMNアノテーション creditSeikyuName */
	public static final String creditSeikyuName_COLUMN = "CREDIT_SEIKYU_NAME";

	//
	// インスタンスフィールド
	//

	private String creditNo;

	private java.math.BigDecimal dataType;

	private java.math.BigDecimal dataTotalDivision;

	private String categoryDivision;

	private java.sql.Timestamp creditDate;

	private java.math.BigDecimal rowNo;

	private String venderCd;

	private String organizationCd;

	private java.math.BigDecimal creditAmount;

	private java.math.BigDecimal eraserAmount;

	private java.math.BigDecimal creditEraserAmount;

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

	private String remark;

	private java.math.BigDecimal approvalStatus;

	private String approvedby;

	private java.sql.Timestamp approvaldate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String debitTitleName;

	private String debitSectionName;

	private String creditTitleName;

	private String creditSectionName;

	private String debitSeikyuCd;

	private String debitSeikyuName;

	private String creditSeikyuCd;

	private String creditSeikyuName;

	//
	// インスタンスメソッド
	//

	/**
	 * creditNo取得.
	 * @return creditNo
	 */
	public String getCreditNo() {
		return this.creditNo;
	}

	/**
	 * creditNo設定.
	 * @param creditNo creditNo
	 */
	public void setCreditNo(final String creditNo) {
		this.creditNo = creditNo;
	}

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
	 * creditDate取得.
	 * @return creditDate
	 */
	public java.sql.Timestamp getCreditDate() {
		return this.creditDate;
	}

	/**
	 * creditDate設定.
	 * @param creditDate creditDate
	 */
	public void setCreditDate(final java.sql.Timestamp creditDate) {
		this.creditDate = creditDate;
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
	 * creditAmount取得.
	 * @return creditAmount
	 */
	public java.math.BigDecimal getCreditAmount() {
		return this.creditAmount;
	}

	/**
	 * creditAmount設定.
	 * @param creditAmount creditAmount
	 */
	public void setCreditAmount(final java.math.BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * eraserAmount取得.
	 * @return eraserAmount
	 */
	public java.math.BigDecimal getEraserAmount() {
		return this.eraserAmount;
	}

	/**
	 * eraserAmount設定.
	 * @param eraserAmount eraserAmount
	 */
	public void setEraserAmount(final java.math.BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * creditEraserAmount取得.
	 * @return creditEraserAmount
	 */
	public java.math.BigDecimal getCreditEraserAmount() {
		return this.creditEraserAmount;
	}

	/**
	 * creditEraserAmount設定.
	 * @param creditEraserAmount creditEraserAmount
	 */
	public void setCreditEraserAmount(final java.math.BigDecimal creditEraserAmount) {
		this.creditEraserAmount = creditEraserAmount;
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
	 * remark取得.
	 * @return remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * remark設定.
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
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
	 * debitTitleName取得.
	 * @return debitTitleName
	 */
	public String getDebitTitleName() {
		return this.debitTitleName;
	}

	/**
	 * debitTitleName設定.
	 * @param debitTitleName debitTitleName
	 */
	public void setDebitTitleName(final String debitTitleName) {
		this.debitTitleName = debitTitleName;
	}

	/**
	 * debitSectionName取得.
	 * @return debitSectionName
	 */
	public String getDebitSectionName() {
		return this.debitSectionName;
	}

	/**
	 * debitSectionName設定.
	 * @param debitSectionName debitSectionName
	 */
	public void setDebitSectionName(final String debitSectionName) {
		this.debitSectionName = debitSectionName;
	}

	/**
	 * creditTitleName取得.
	 * @return creditTitleName
	 */
	public String getCreditTitleName() {
		return this.creditTitleName;
	}

	/**
	 * creditTitleName設定.
	 * @param creditTitleName creditTitleName
	 */
	public void setCreditTitleName(final String creditTitleName) {
		this.creditTitleName = creditTitleName;
	}

	/**
	 * creditSectionName取得.
	 * @return creditSectionName
	 */
	public String getCreditSectionName() {
		return this.creditSectionName;
	}

	/**
	 * creditSectionName設定.
	 * @param creditSectionName creditSectionName
	 */
	public void setCreditSectionName(final String creditSectionName) {
		this.creditSectionName = creditSectionName;
	}

	/**
	 * debitSeikyuCd取得.
	 * @return debitSeikyuCd
	 */
	public String getDebitSeikyuCd() {
		return this.debitSeikyuCd;
	}

	/**
	 * debitSeikyuCd設定.
	 * @param debitSeikyuCd debitSeikyuCd
	 */
	public void setDebitSeikyuCd(final String debitSeikyuCd) {
		this.debitSeikyuCd = debitSeikyuCd;
	}

	/**
	 * debitSeikyuName取得.
	 * @return debitSeikyuName
	 */
	public String getDebitSeikyuName() {
		return this.debitSeikyuName;
	}

	/**
	 * debitSeikyuName設定.
	 * @param debitSeikyuName debitSeikyuName
	 */
	public void setDebitSeikyuName(final String debitSeikyuName) {
		this.debitSeikyuName = debitSeikyuName;
	}

	/**
	 * creditSeikyuCd取得.
	 * @return creditSeikyuCd
	 */
	public String getCreditSeikyuCd() {
		return this.creditSeikyuCd;
	}

	/**
	 * creditSeikyuCd設定.
	 * @param creditSeikyuCd creditSeikyuCd
	 */
	public void setCreditSeikyuCd(final String creditSeikyuCd) {
		this.creditSeikyuCd = creditSeikyuCd;
	}

	/**
	 * creditSeikyuName取得.
	 * @return creditSeikyuName
	 */
	public String getCreditSeikyuName() {
		return this.creditSeikyuName;
	}

	/**
	 * creditSeikyuName設定.
	 * @param creditSeikyuName creditSeikyuName
	 */
	public void setCreditSeikyuName(final String creditSeikyuName) {
		this.creditSeikyuName = creditSeikyuName;
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

